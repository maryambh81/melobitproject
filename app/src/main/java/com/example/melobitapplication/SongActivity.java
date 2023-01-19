package com.example.melobitapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melobitapplication.Api.ApiService;
import com.example.melobitapplication.model.ArtistModel;
import com.example.melobitapplication.model.SongModelList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SongActivity extends AppCompatActivity {
    private static final String TAG = "SongActivity";
    TextView txtxTitel;
    String key;
    String flag="song";
    RecyclerView recyclecSong;
    String path;
    AlertDialog alertDialog;


    List<SongModelList> songModelList;
    List<ArtistModel> artistModels;

    AdaptorSongList adaptorSongList;
    ///


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_recyclerview);

        txtxTitel = findViewById(R.id.txtTitel);
        recyclecSong = findViewById(R.id.recyclecSong);
        songModelList = new ArrayList<>();
        artistModels = new ArrayList<>();


        // get key
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        txtxTitel.setText(key);


        try
        {
            getSongPath();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        requestSong();
        showAlertLoadingData();
        setAdapter();

    }


    public  void requestSong()
    {

//        Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(path+"/")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getSongs().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String data = "";
                try {
                    data = response.body().string();
                    showJsonSong(data);
                    alertDialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SongActivity.this,"connection to service failed",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    // Show data Song
    public void showJsonSong(String song)
    {
        Gson gson =new Gson();
        JsonObject jsonObject = gson.fromJson(song,JsonObject.class);

        JsonArray jsonArrayView = jsonObject.getAsJsonArray("results");
        if(flag.equals("Song"))
        {
            SongModelList[] modelSong = gson.fromJson(jsonArrayView, SongModelList[].class);
            songModelList.addAll(Arrays.asList(modelSong));
            adaptorSongList.notifyDataSetChanged();
        }
        ///



    }

    // set Adapter
    public  void setAdapter()
    {
        if(flag.equals("Song"))
        {
            adaptorSongList = new AdaptorSongList(this, songModelList);

            recyclecSong.setAdapter(adaptorSongList);
            recyclecSong.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclecSong.setHasFixedSize(true);
        }
        ///


    }


    void getSongPath() throws IOException
    {
        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray jsonItem = obj.getJSONArray("item");

            for (int i = 0; i < jsonItem.length(); i++)
            {
                JSONObject jsonObjectSong = jsonItem.getJSONObject(i);

               if(jsonObjectSong.getString("name").equals(key))
               {
                   JSONObject jsonRequest = new JSONObject(jsonObjectSong.getString("request"));
                   JSONObject jsonUrl = new JSONObject(jsonRequest.getString("url"));
                   path = jsonUrl.getString("raw");
                   txtxTitel.setText(key);
                   if (key.equals("Trending Artist"))
                   {
                       flag = "Artist";
                   }
                   else
                   {
                       flag = "Song";
                   }

               }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String loadJSONFromAsset()
    {
        String json = null;
        try {
            InputStream is = getAssets().open("Melobit.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // ProgressDialog
    public void showAlertLoadingData()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.loading_data,null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }
}

