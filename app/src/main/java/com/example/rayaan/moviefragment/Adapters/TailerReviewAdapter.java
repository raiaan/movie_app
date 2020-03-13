package com.example.rayaan.moviefragment.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rayaan.moviefragment.Model.ResultReview;
import com.example.rayaan.moviefragment.Model.ResultTailers;
import com.example.rayaan.moviefragment.Model.Review;
import com.example.rayaan.moviefragment.R;
import com.example.rayaan.moviefragment.ViewHolders.MidHolder;
import com.example.rayaan.moviefragment.ViewHolders.ReviewButtomHolder;
import com.example.rayaan.moviefragment.ViewHolders.TailerViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Rayaan on 18/08/2016.
 */
public class TailerReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int Mid = 2;
    private List<ResultTailers> tailer;
    private List<ResultReview>reviews;
    Context  context;
    public TailerReviewAdapter(List<ResultTailers> tailer, List<ResultReview>reviews, Context context) {
        this.tailer = tailer;
        this.reviews=reviews;
        this.context=context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_ITEM)
        {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.tailer_item,parent,false);
            return new TailerViewHolder(view);
        }
        else if(viewType==Mid){
            return new MidHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.review_starting,parent,false));
        }
           else return new ReviewButtomHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.review,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position)==TYPE_ITEM){
            TailerViewHolder tailerViewHolder=(TailerViewHolder)holder;
            ((TailerViewHolder) holder).tailerName.setText(tailer.get(position).getName());
            ((TailerViewHolder) holder).imageView.setImageResource(R.mipmap.unnamed);
            tailerViewHolder.layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.youtube.com/watch?v="+tailer.get(position).getKey()
                    ));
                    context.startActivity(myIntent);
                }
            });
        }else if (getItemViewType(position)==TYPE_FOOTER){
            Log.v("x",reviews.get(position-tailer.size()-1).getAuthor());
            ReviewButtomHolder reviewButtomHolder=(ReviewButtomHolder)holder;
            reviewButtomHolder.content.setText(reviews.get(position-1-tailer.size()).getContent());
            reviewButtomHolder.author.setText(reviews.get(position-1-tailer.size()).getAuthor());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position<tailer.size())
            return TYPE_ITEM;
        else if(position==tailer.size())
            return Mid;
        else
        {

            return TYPE_FOOTER;
        }
    }

    @Override
    public int getItemCount() {
        return (tailer.size()+reviews.size()+1);
    }

}
