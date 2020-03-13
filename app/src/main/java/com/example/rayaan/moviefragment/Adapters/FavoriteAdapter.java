package com.example.rayaan.moviefragment.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.rayaan.moviefragment.Model.Movie;
import com.example.rayaan.moviefragment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rayaan on 20/08/2016.
 */
public class FavoriteAdapter extends BaseAdapter {
    Context context;
    List<String> poster_moveis;

    public FavoriteAdapter(Context context, List<String> poster_moveis) {
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
        if(poster_moveis.size()>0){
            if (view == null) {
                imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) view;

            }
            Picasso.with(context).load(poster_moveis.get(i)).into(imageView);
            return imageView;
        }
        return null;
    }
    public static class MovieFavorite extends RecyclerView.ViewHolder {
        @Bind(R.id.poster_img)
        ImageView poster_img;


        public MovieFavorite(View v) {
            super(v);
            ButterKnife.bind(v);
        }
    }
}
