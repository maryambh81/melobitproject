package com.example.melobitapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
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


        imgSearch.setOnClickListener(this);
        imgNewSong.setOnClickListener(this);
        imgTrendingArtist.setOnClickListener(this);



    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgSearch: {
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
        }
    }


}