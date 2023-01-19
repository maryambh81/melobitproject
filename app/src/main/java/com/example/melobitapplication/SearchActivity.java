package com.example.melobitapplication;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melobitapplication.Api.ApiService;
import com.example.melobitapplication.model.SearchModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends AppCompatActivity
{

    private static final String TAG = "SearchActivity";
    EditText edSearch;

    RecyclerView recyclerViewSearch;

    List<SearchModel> searchModels;

    AdaptorSearchSong adaptorSongList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edSearch = findViewById(R.id.edSearch);
        recyclerViewSearch = findViewById(R.id.recyclerViewSearch);

        searchModels = new ArrayList<>();


        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                 new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(charSequence.toString().trim().equals(""))
                    {
                        recyclerViewSearch.setVisibility(View.GONE);
                    }
                    else
                    {
                        search(charSequence.toString().trim());
                    }
                }
            },1000);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setAdapter();

    }

    public  void search(String key)
    {
//        Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-beta.melobit.com")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.searchSongs(key).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String data = "";
                try {
                    data= response.body().string();
                    showJsonSong(data);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "onResponse: " + data);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SearchActivity.this,"connection to service failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showJsonSong(String song) throws JSONException
    {
        Gson gson =new Gson();
        JsonObject jsonObject = gson.fromJson(song,JsonObject.class);

        JsonArray jsonArrayView = jsonObject.getAsJsonArray("results");

            SearchModel[] searchModels1 = gson.fromJson(jsonArrayView,SearchModel[].class);
            searchModels.addAll(Arrays.asList(searchModels1));
            adaptorSongList.notifyDataSetChanged();

    }

    public  void setAdapter() {
        adaptorSongList = new AdaptorSearchSong(this, searchModels);
        recyclerViewSearch.setAdapter(adaptorSongList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewSearch.setHasFixedSize(true);
    }




}