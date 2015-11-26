package com.pietertolsma.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pietertolsma on 11/25/15.
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {

    private MovieItem movie;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView =  v;
        }
    }

    public DetailAdapter(MovieItem movie){
        this.movie = movie;
    }

    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_view_item, parent, false);



        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        switch(position){
            case 0:
                ((TextView) holder.mView.findViewById(R.id.info_text)).setText(movie.description);
                break;
            case 1:
                ((TextView) holder.mView.findViewById(R.id.info_text)).setText(movie.releaseDate);
            default:

                break;
        }
    }

    @Override
    public int getItemCount(){
        return 2;
    }
}
