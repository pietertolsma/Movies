package com.pietertolsma.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pietertolsma on 11/24/15.
 */
public class MovieAdapter extends ArrayAdapter<MovieItem> {

    public MovieAdapter(Context context, int textViewResourceId){
        super(context, textViewResourceId);
    }

    public MovieAdapter(Context context, List<MovieItem> items){
        super(context,0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        MovieItem movie = getItem(position);
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.movie_grid_item, parent, false);
        }
        ImageView poster = (ImageView) v.findViewById(R.id.movie_image);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500/" + movie.imageUrl).into(poster);
        return v;
    }

    public void whipe(){
        clear();
    }

}
