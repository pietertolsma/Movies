package com.pietertolsma.movies;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pietertolsma on 11/24/15.
 */
public class FetchMoviesTask extends AsyncTask<String, Void, MovieItem[]>{

    private static String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    private static String API_KEY = R.string.api_key;


    private MovieAdapter adapter;

    public FetchMoviesTask(MovieAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    public MovieItem[] doInBackground(String... params){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;

        int numMovies = 20;

        try{
            final String baseURL = "http://api.themoviedb.org/3/discover/movie?";
            final String option_param = "sort_by";
            final String option_value = "popularity.desc";
            final String api_param = "api_key";

            Uri buildUri = Uri.parse(baseURL).buildUpon()
                    .appendQueryParameter(option_param, option_value)
                    .appendQueryParameter(api_param, API_KEY)
                    .build();
            URL url = new URL(buildUri.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }
            if(buffer.length() == 0){
                return null;
            }
            result = buffer.toString();
        }catch(IOException e){
            Log.d(LOG_TAG, e.getMessage());
        }finally{
            if(connection != null){
                connection.disconnect();
            }
            if(reader != null){
                try{
                    reader.close();
                }catch(IOException e){
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try{
            return getMovieDataFromJson(result, numMovies);
        }catch(JSONException e){
            Log.e(LOG_TAG, "Error parsing json", e);
        }

        return null;
    }

    private MovieItem[] getMovieDataFromJson(String result,int numMovies)
            throws JSONException {

        //List of data that needs to be extracted
        final String MOVIE_ARRAY = "results";
        final String IMAGE_URL = "poster_path";
        final String TITLE = "title";
        final String DESCRIPTION = "overview";
        final String MOVIE_ID = "id";
        final String RATING = "vote_average";
        final String RELEASE_DATE = "release_date";

        JSONObject jsonObj = new JSONObject(result);
        JSONArray movieArray = jsonObj.getJSONArray(MOVIE_ARRAY);

        MovieItem[] resultMovies = new MovieItem[numMovies];

        for(int i = 0; i < numMovies; i++){
            String imageUrl;
            String description;
            String title;
            String movieId;
            String rating;
            String releaseDate;

            JSONObject movie = movieArray.getJSONObject(i);
            imageUrl = movie.getString(IMAGE_URL);
            description = movie.getString(DESCRIPTION);
            title = movie.getString(TITLE);
            movieId = movie.getString(MOVIE_ID);
            releaseDate = movie.getString(RELEASE_DATE);
            rating = movie.getString(RATING);
            Log.d(LOG_TAG, title);

            resultMovies[i] = new MovieItem(title, releaseDate, rating, imageUrl, movieId, description, i);
        }

        return resultMovies;
    }

    @Override
    protected void onPostExecute(MovieItem[] results){
        if(results != null) {
            adapter.whipe();
            for (MovieItem movie : results) {
               adapter.add(movie);
            }
        }

    }
}
