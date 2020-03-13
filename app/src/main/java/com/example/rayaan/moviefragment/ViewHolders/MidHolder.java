package com.example.rayaan.moviefragment.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rayaan.moviefragment.R;

/**
 * Created by Rayaan on 04/09/2016.
 */
public class MidHolder extends RecyclerView.ViewHolder  {
    TextView review_begien;
    public MidHolder(View itemView) {
        super(itemView);
        review_begien=(TextView)itemView.findViewById(R.id.review_starting);
    }
}
