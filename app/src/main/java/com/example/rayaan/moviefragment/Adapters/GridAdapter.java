package com.example.rayaan.moviefragment.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.rayaan.moviefragment.Model.Movie;
import com.example.rayaan.moviefragment.R;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rayaan on 08/08/2016.
 */
public class GridAdapter extends BaseAdapter {
    Context context;
    List<Movie>poster_moveis;

    public GridAdapter(Context context, List<Movie> poster_moveis) {
        this.context = context;
        this.poster_moveis = poster_moveis;
    }

    @Override
    public int getCount() {
        return poster_moveis.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) view;

        }
        Picasso.with(context).load(poster_moveis.get(i).getPosterPath()).into(imageView);
        return imageView;
    }
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.poster_img)
        ImageView poster_img;


        public MovieViewHolder(View v) {
            super(v);
            ButterKnife.bind(v);
        }
    }
}
