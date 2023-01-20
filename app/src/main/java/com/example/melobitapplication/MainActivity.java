package com.example.melobitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    ImageView imgTop10WeekSong,imgTop10DaySong,imgTrendingArtist,imgNewSong,imglatestSliders;
    SearchView seearchview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seearchview =findViewById(R.id.search);
        imgNewSong = findViewById(R.id.imgNewSong);
        imgTrendingArtist = findViewById(R.id.imgTrendingArtist);
        imgTop10DaySong = findViewById(R.id.imgTop10DaySong);
        imgTop10WeekSong = findViewById(R.id.imgTop10WeekSong);
        imglatestSliders = findViewById(R.id.imgLatestSliders);


        seearchview.setOnClickListener(this);
        imgNewSong.setOnClickListener(this);
        imgTrendingArtist.setOnClickListener(this);
        imgTop10WeekSong.setOnClickListener(this);
        imgTop10DaySong.setOnClickListener(this);
        imglatestSliders.setOnClickListener(this);




    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search: {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));

                break;
            }

            case R.id.imgNewSong:
            {
                Intent intent = new Intent(MainActivity.this,SongActivity.class);
                intent.putExtra("key","Latest Song");
                startActivity(intent);

                break;
            }

            case R.id.imgTrendingArtist:
            {
                Intent intent = new Intent(MainActivity.this,SongActivity.class);
                intent.putExtra("key","Trending Artist");
                startActivity(intent);
                break;
            }
            case R.id.imgTop10DaySong:
            {
                Intent intent = new Intent(MainActivity.this,SongActivity.class);
                intent.putExtra("key","Top 10 Day Song");
                startActivity(intent);
                break;
            }

            case R.id.imgTop10WeekSong:
            {
                Intent intent = new Intent(MainActivity.this,SongActivity.class);
                intent.putExtra("key","Top 10 Week Song");
                startActivity(intent);
                break;
            }
            case R.id.imgLatestSliders:
            {
                Intent intent = new Intent(MainActivity.this,SongActivity.class);
                intent.putExtra("key","Latest Sliders");
                startActivity(intent);
                break;
            }
        }
    }


}