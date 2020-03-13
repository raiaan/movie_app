package com.example.rayaan.moviefragment.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.rayaan.moviefragment.R;

/**
 * Created by Rayaan on 18/08/2016.
 */
public class ReviewButtomHolder extends RecyclerView.ViewHolder {
    public TextView content;
    public TextView author;
    public ReviewButtomHolder(View itemView) {
        super(itemView);
        content=(TextView)itemView.findViewById(R.id.review_content);
        author=(TextView)itemView.findViewById(R.id.author);
    }
}
