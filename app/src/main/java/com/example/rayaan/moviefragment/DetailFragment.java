package com.example.rayaan.moviefragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rayaan.moviefragment.Adapters.TailerReviewAdapter;
import com.example.rayaan.moviefragment.Connect.API;
import com.example.rayaan.moviefragment.Connect.ApiInterface;
import com.example.rayaan.moviefragment.Model.Movie;
import com.example.rayaan.moviefragment.Model.ResultReview;
import com.example.rayaan.moviefragment.Model.ResultTailers;
import com.example.rayaan.moviefragment.Model.Review;
import com.example.rayaan.moviefragment.Model.Tailers;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rayaan on 17/08/2016.
 */
public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment,container,false);
        DetailFragment detailFragment=(DetailFragment) getFragmentManager().findFragmentById(R.id.left_tablet_fragment);
        ButterKnife.bind(this,view);
        if (savedInstanceState!=null&&detailFragment!=null){
            updateView(savedInstanceState.getString("poster"),savedInstanceState.getString("title"),
                    savedInstanceState.getString("date"),savedInstanceState.getDouble("vote"),
                    savedInstanceState.getString("overview"),savedInstanceState.getInt("id"));
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(movie!=null){
            outState.putString("poster",movie.getPosterPath());
            outState.putString("title",movie.getOriginalTitle());
            outState.putString("date",movie.getReleaseDate());
            outState.putDouble("vote", movie.getVoteAverage());
            outState.putString("overview",movie.getOverview());
            outState.putInt("id",movie.getId());
        }
        else{
            outState.putString("poster",poster_path);
            outState.putString("title",title.getText().toString());
            outState.putString("date",date.getText().toString());
            outState.putDouble("vote", Double.parseDouble(vote_average.getText().toString()));
            outState.putString("overview",overoview.getText().toString());
            outState.putInt("id",id);
        }
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        if (savedInstanceState!=null){
//            updateView(savedInstanceState.getString("poster"),savedInstanceState.getString("title"),
//                    savedInstanceState.getString("date"),savedInstanceState.getDouble("vote"),
//                    savedInstanceState.getString("overview"),savedInstanceState.getInt("id"));
//        }
//    }

    public void saveFavorite(String poster_param){
        SharedPreferences favorite;
        final String favFile="favFile";
        favorite = getActivity().getSharedPreferences(favFile,getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor= favorite.edit() ;
        String favorite_movie_list=favorite.getString("urls",null);
        if(favorite_movie_list==null)
        {
            editor.putString("urls",poster_param);

        }else{
            if(!favorite_movie_list.contains(poster_param)){
                String append=favorite_movie_list;
                editor.putString("urls",append+" "+poster_param);
            }
            else {
                Toast.makeText(getActivity(),
                        "the film already exist as favorite film",
                        Toast.LENGTH_LONG).show();
            }
        }
        editor.commit();
    }


    public void updateView(final Movie movie){
        this.movie=new Movie(movie.getVoteAverage(),movie.getOriginalTitle(),
                movie.getId(),movie.getReleaseDate(),
                movie.getOverview(),movie.getPosterPath());
        Picasso.with(getActivity()).load(movie.getPosterPath()).into(poster);
        title.setText(movie.getOriginalTitle());
        date.setText(movie.getReleaseDate());
        vote_average.setText(movie.getVoteAverage().toString());
        overoview.setText(overoview.getText().toString()+"\n"+movie.getOverview());
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = API.getClient().create(ApiInterface.class);
        id=movie.getId();
        reviewCall=apiInterface.getreviews(id,MainFragment.API_KEY);
        tailersCall = apiInterface.getTailers(id,MainFragment.API_KEY);
        tailersCall.enqueue(new Callback<Tailers>() {
            @Override
            public void onResponse(Call<Tailers> call, Response<Tailers> response) {
                tailerse = response.body().getResults();
                reviewCall.enqueue(new Callback<Review>() {
                    @Override
                    public void onResponse(Call<Review> call, Response<Review> response) {
                        reviews=response.body().getResultReviews();
                        recyclerView.setAdapter(new TailerReviewAdapter(tailerse,reviews,getActivity()));

                    }

                    @Override
                    public void onFailure(Call<Review> call, Throwable t) {
                        Toast.makeText(getActivity(),"failer to get review",Toast.LENGTH_LONG).show();
                    }
                });
            }
            @Override
            public void onFailure(Call<Tailers> call, Throwable t) {
                Toast.makeText(getActivity(),"failer to get tailer",Toast.LENGTH_LONG).show();}
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   saveFavorite(movie.getPosterPath());

        }});
    }
    public void updateView(String poster_path, String title, String date, double vote, String overview, int id){
        this.poster_path=poster_path;
        this.id=id;
        Picasso.with(getActivity()).load(poster_path).into(poster);
        this.title.setText(title);
        this.date.setText(date);
        vote_average.setText(vote+" ");
        this.overoview.setText(overoview.getText().toString()+"\n"+overview);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        apiInterface = API.getClient().create(ApiInterface.class);
        reviewCall=apiInterface.getreviews(id,MainFragment.API_KEY);
        tailersCall = apiInterface.getTailers(id,MainFragment.API_KEY);
        tailersCall.enqueue(new Callback<Tailers>() {
            @Override
            public void onResponse(Call<Tailers> call, Response<Tailers> response) {
                tailerse = response.body().getResults();
                reviewCall.enqueue(new Callback<Review>() {
                    @Override
                    public void onResponse(Call<Review> call, Response<Review> response) {
                        reviews=response.body().getResultReviews();
                        recyclerView.setAdapter(new TailerReviewAdapter(tailerse,reviews,getActivity()));

                    }

                    @Override
                    public void onFailure(Call<Review> call, Throwable t) {
                        Toast.makeText(getActivity(),"failer to get review",Toast.LENGTH_LONG).show();
                    }
                });
            }
            @Override
            public void onFailure(Call<Tailers> call, Throwable t) {
                Toast.makeText(getActivity(),"failer to get tailer",Toast.LENGTH_LONG).show();}
        });
        img_path=poster_path;
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  saveFavorite(img_path);
            }
        });
    }
    Movie movie;
    String poster_path;
    @Bind(R.id.poster_detail)
    ImageView poster;
    @Bind(R.id.orignal_title)
    TextView title;
    @Bind(R.id.date)
    TextView date;
    @Bind(R.id.vote_average)
    TextView vote_average;
    @Bind(R.id.overview)
    TextView overoview;
    @Bind(R.id.tailer_rec)
    RecyclerView recyclerView;
    @Bind(R.id.favorit)
    Button fav;
    int id;
    RecyclerView.LayoutManager layoutManager;
    List<ResultTailers> tailerse;
    List<ResultReview>reviews;
    ApiInterface apiInterface;
    Call<Review> reviewCall;
    Call<Tailers>tailersCall;
    String img_path;

}
