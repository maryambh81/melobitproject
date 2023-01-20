package com.example.melobitapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.melobitapplication.model.SearchModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptorSearchSong extends RecyclerView.Adapter<AdaptorSearchSong.MyHolder> {
    Context context;
    List<SearchModel> searchModels;


    public AdaptorSearchSong(Context context, List<SearchModel> searchModels) {
        this.context = context;
        this.searchModels = searchModels;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        SearchModel positions = searchModels.get(position);

        if (positions.getType().equals("song")) {
            Picasso.get().load(positions.getSong().getImage().getThumbnail_small().getUrl()).into(holder.imgSong);
            holder.txtName_Song.setText(positions.getSong().getTitle());
            holder.txtName_Artist.setText(positions.getSong().getArtists().get(0).getFullName());
            holder.txtName_Song.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PlaySongActivity.class);
                    intent.putExtra("songId", positions.getSong().getId());

                    context.startActivity(intent);

                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgSong;
        TextView txtName_Artist, txtName_Song;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtName_Song = itemView.findViewById(R.id.txtName_Song);
            txtName_Artist = itemView.findViewById(R.id.txtName_Artist);
            imgSong = itemView.findViewById(R.id.imgSong);


        }
    }
}
