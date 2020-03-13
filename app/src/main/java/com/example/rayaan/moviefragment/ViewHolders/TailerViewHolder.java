package com.example.rayaan.moviefragment.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rayaan.moviefragment.R;

/**
 * Created by Rayaan on 18/08/2016.
 */
public class TailerViewHolder extends RecyclerView.ViewHolder{
    public TextView tailerName;
    public LinearLayout layoutItem;
    public ImageView imageView;

    public TailerViewHolder(View itemView) {
        super(itemView);
        imageView=(ImageView)itemView.findViewById(R.id.tailer_action);
        layoutItem=(LinearLayout)itemView.findViewById(R.id.tailer_item);
        tailerName=(TextView)itemView.findViewById(R.id.tailer_name);
    }
}
