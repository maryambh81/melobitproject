package com.example.melobitapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.melobitapplication.Api.ApiService;
import com.example.melobitapplication.model.DetailSongModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaySongActivity extends AppCompatActivity {
    private static final String TAG = "DetailSongActivity";
    ImageView imgSong, imgPlay;
    TextView txtNameSong, txtNameArtist, txtDate, txtNumberPlay, textSong;
    TextView tvCurrentTime, tvTotalTime;
    SeekBar seekBar;


    DetailSongModel detailSongModel;

    String SongId;
    MediaPlayer mPlayer;
    String play = "play";
    boolean stateMpcreate = false;

    Uri pathAudio;
    Runnable runnable;
    Handler handler;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        imgPlay = findViewById(R.id.imgPlay);
        imgSong = findViewById(R.id.imgSong);
        txtNameSong = findViewById(R.id.txtNameSong);
        txtNameArtist = findViewById(R.id.txtNameArtist);
        tvCurrentTime = findViewById(R.id.tv_current_time);
        tvTotalTime = findViewById(R.id.tv_total_time);
        txtDate = findViewById(R.id.txtDate);
        txtNumberPlay = findViewById(R.id.txtNumberPlay);
        textSong = findViewById(R.id.textSong);
        seekBar = findViewById(R.id.sekBar);
        handler = new Handler();
        playCycle();


        Intent intent = getIntent();
        SongId = intent.getStringExtra("songId");
        requestSong();
        showAlert();

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (play.equals("play")) {
                    mPlayer.start();
                    seekBar.setMax(mPlayer.getDuration());
                    playCycle();
                    imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                    tvTotalTime.setText(getTimeFormatted(mPlayer.getDuration()));
                    play = "pause";
                    stateMpcreate = true;
                } else if (play.equals("pause")) {
                    mPlayer.pause();
                    imgPlay.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    play = "play";
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mPlayer.seekTo(progress);
                    tvCurrentTime.setText(getTimeFormatted(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    // method get data
    public void requestSong() {
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api-beta.melobit.com")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getDetailSong(SongId).enqueue(new Callback<DetailSongModel>() {
            @Override
            public void onResponse(Call<DetailSongModel> call, retrofit2.Response<DetailSongModel> response) {
                detailSongModel = response.body();
                txtNameSong.setText(detailSongModel.getTitle());
                txtNameArtist.setText((detailSongModel.getArtists().get(0).getFullName()));
                txtNumberPlay.setText(detailSongModel.getDownloadCount());

                textSong.setText(detailSongModel.getLyrics());
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

               //تاریخ انتشار
                txtDate.setText(String.format(detailSongModel.getReleaseDate(), df));
                Picasso.get().load(detailSongModel.getImage().getCover().getUrl()).into(imgSong);
                pathAudio = Uri.parse(detailSongModel.getAudio().getMedium().getUrl());

                Toast.makeText(PlaySongActivity.this, "loding music please wait .... ", Toast.LENGTH_SHORT).show();
                mPlayer = MediaPlayer.create(PlaySongActivity.this, pathAudio);

                alertDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DetailSongModel> call, Throwable t) {
                Toast.makeText(PlaySongActivity.this, "connection to service failed", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

    }

    public void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.loading_data, null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

    }


    public void playCycle() {
        try {
            seekBar.setProgress(mPlayer.getCurrentPosition());
            tvCurrentTime.setText(getTimeFormatted(mPlayer.getCurrentPosition()));
            if (mPlayer.isPlaying()) {
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        playCycle();


                    }
                };
                handler.postDelayed(runnable, 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method format time music
    private String getTimeFormatted(long milliSeconds) {
        String finalTimerString = "";
        String secondsString;

        //Converting total duration into time
        int hours = (int) (milliSeconds / 3600000);
        int minutes = (int) (milliSeconds % 3600000) / 60000;
        int seconds = (int) ((milliSeconds % 3600000) % 60000 / 1000);

        // Adding hours if any
        if (hours > 0)
            finalTimerString = hours + ":";

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10)
            secondsString = "0" + seconds;
        else
            secondsString = "" + seconds;

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // Return timer String;
        return finalTimerString;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (stateMpcreate) {
            if (mPlayer.isPlaying()) {
                mPlayer.stop();
            }
        }

    }
}