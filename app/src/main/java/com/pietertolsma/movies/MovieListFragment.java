package com.pietertolsma.movies;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class MovieListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private MovieAdapter adapter;

    private ArrayList<MovieItem> movies = new ArrayList<MovieItem>();

    public MovieListFragment() {
    }

    @Override
    public void onStart(){
        super.onStart();
        fetchMovies();
    }

    private void fetchMovies(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        new FetchMoviesTask(adapter).execute("best");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movielist_grid, container, false);
        adapter = new MovieAdapter(getActivity(), movies);

        GridView gridView = (GridView) view.findViewById(R.id.movie_list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id){
//        Intent viewDetailsIntent = new Intent(this.getActivity(), DetailActivity.class);
//        MovieItem movie = movies.get(position);
//        viewDetailsIntent.putExtra(MainActivity.EXTRA_INT_POSITION, position);
//        viewDetailsIntent.putExtra(MainActivity.EXTRA_STRING_DESCRIPTION, movie.description);
//        viewDetailsIntent.putExtra(MainActivity.EXTRA_STRING_MOVIE_ID, movie.movieId);
//        viewDetailsIntent.putExtra(MainActivity.EXTRA_STRING_TITLE, movie.movieName);
//        viewDetailsIntent.putExtra(MainActivity.EXTRA_STRING_RATING, movie.rating);
//        viewDetailsIntent.putExtra(MainActivity.EXTRA_STRING_RELEASE, movie.releaseDate);
//        startActivity(viewDetailsIntent);
    }


}
