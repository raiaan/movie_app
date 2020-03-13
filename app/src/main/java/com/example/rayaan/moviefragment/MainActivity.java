package com.example.rayaan.moviefragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.rayaan.moviefragment.Model.Movie;

public class MainActivity extends AppCompatActivity implements MainFragment.OnGridItemSelected{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    public void gridItemSelected(Movie movie) {
        detailFragment=(DetailFragment) getFragmentManager().findFragmentById(R.id.left_tablet_fragment);
        if(detailFragment!=null)
        {

            detailFragment.updateView(movie);
        }
        else{
            Intent intent = new Intent(this,Detail.class);
            intent.putExtra("poster_path",movie.getPosterPath());
            intent.putExtra("title",movie.getOriginalTitle());
            intent.putExtra("date",movie.getReleaseDate());
            intent.putExtra("vote",movie.getVoteAverage());
            intent.putExtra("overview",movie.getOverview());
            intent.putExtra("id",movie.getId());
            startActivity(intent);
        }
    }
    DetailFragment detailFragment;
    Movie movie;
}
