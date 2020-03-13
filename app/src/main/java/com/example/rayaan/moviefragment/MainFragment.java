package com.example.rayaan.moviefragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rayaan.moviefragment.Adapters.FavoriteAdapter;
import com.example.rayaan.moviefragment.Adapters.GridAdapter;
import com.example.rayaan.moviefragment.Connect.API;
import com.example.rayaan.moviefragment.Connect.ApiInterface;
import com.example.rayaan.moviefragment.Model.Movie;
import com.example.rayaan.moviefragment.Model.MoviesResponse;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rayaan on 17/08/2016.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemSelectedListener,Callback<MoviesResponse>{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.main_fragment,container,false);
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("selected item",getActivity().MODE_PRIVATE);
        int position = sharedPreferences.getInt("item name",0);
        spinnerImplemntation(view);
        spinner.setSelection(position);
        spinner.setOnItemSelectedListener(this);
        getResult(spinner.getSelectedItem().toString());
        gridview=(GridView) view.findViewById(R.id.grid_view);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(movies.get(i)!=null)
                    onGridItemSelected.gridItemSelected(movies.get(i));
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        try{
            onGridItemSelected=(OnGridItemSelected) context;
        }catch (Exception e){

        }

    }

    public void spinnerImplemntation(View view){
        LinearLayout relativeLayout=(LinearLayout) view.findViewById(R.id.layout);
        spinner = (Spinner) relativeLayout.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter= ArrayAdapter.createFromResource(getActivity(),R.array.spinner_value,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        sort=spinner.getSelectedItem().toString();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sort = adapterView.getItemAtPosition(i).toString();
        getResult(sort);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("selected item",getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("item name",spinner.getSelectedItemPosition());
        editor.commit();

    }
    public void getResult(String Type){
        apiService = API.getClient().create(ApiInterface.class);
        if (Type.equals("Top rated")) {

            call = apiService.getTopRatedMovies(API_KEY);
            call.enqueue(this);
        } else if(Type.equals("most Popular")){
            call = apiService.getPopularMovies(API_KEY);
            call.enqueue(this);
        }
        else{
            if(readFav()==null)
            {
                Toast.makeText(getActivity(),"there is no favorite movie ", Toast.LENGTH_SHORT).show();
                spinner.setSelection(0);
            }
            else
                gridview.setAdapter(new FavoriteAdapter(getActivity(), Arrays.asList(readFav())));
        }



    }
    @Nullable
    public  String[] readFav(){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("favFile",getActivity().MODE_PRIVATE);
        String favorite=sharedPreferences.getString("urls",null);
        if(favorite!=null)
        {
            String []fav_movie_array=favorite.split(" ");
            return fav_movie_array;
        }
        return null;
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
        movies = response.body().getResults();
        gridview.setAdapter(new GridAdapter(getActivity(),movies));
    }

    @Override
    public void onFailure(Call<MoviesResponse> call, Throwable t) {
        Toast.makeText(getActivity(),"no internt conection",Toast.LENGTH_LONG).show();
    }
    public interface OnGridItemSelected{
        public void gridItemSelected(Movie movie);
    }
   public final static String API_KEY = "b4dc790fe4455d2de33988fec71923a1";
     String sort=" ";
     OnGridItemSelected onGridItemSelected;
     Call<MoviesResponse> call;
     ApiInterface apiService;
     GridView gridview;
     List<Movie> movies;
     Spinner spinner;
}
