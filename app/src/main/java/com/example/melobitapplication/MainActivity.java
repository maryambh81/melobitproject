package com.example.melobitapplication;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    ImageView imgTop10WeekSong,imgTop10DaySong,imgTrendingArtist,imgNewSong,imglatestSliders,
            imgSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSearch = findViewById(R.id.imgSearch);
        imgNewSong = findViewById(R.id.imgNewSong);
        imgTrendingArtist = findViewById(R.id.imgTrendingArtist);
        imgTop10DaySong = findViewById(R.id.imgTop10DaySong);
        imgTop10WeekSong = findViewById(R.id.imgTop10WeekSong);
        imglatestSliders = findViewById(R.id.imgLatestSliders);



    }


}