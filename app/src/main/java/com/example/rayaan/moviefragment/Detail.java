package com.example.rayaan.moviefragment;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle p =getIntent().getExtras();
        String poster=p.getString("poster_path");
        String title=p.getString("title");
        String date=p.getString("date");
        double vote=p.getDouble("vote");
        Log.v("vote",vote+" ");
        String overview=p.getString("overview");
        int id=p.getInt("id");
        DetailFragment detailFragment=(DetailFragment) getFragmentManager().
                findFragmentById(R.id.detail_fragment);
        detailFragment.updateView(poster,title,date,vote,overview,id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
