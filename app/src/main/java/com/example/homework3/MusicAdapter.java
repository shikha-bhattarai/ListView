package com.example.homework3;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    Context context;

    ArrayList<MusicList> musicData;
    public MusicAdapter(ArrayList<MusicList> musicData) {
        this.musicData = musicData;
    }
    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.music_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {
        MusicList music = musicData.get(position);
        holder.textViewArtist.setText("Artist: "+music.artistName);
        holder.textViewTitle.setText("Name: "+music.trackName);
        holder.textViewPrice.setText("Price: " +String.valueOf(music.trackPrice));
        holder.textViewDate.setText("Date:"+ music.releaseDate);
    }

    @Override
    public int getItemCount() {
        return musicData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewArtist, textViewPrice, textViewDate;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            textViewArtist = (TextView) itemView.findViewById(R.id.textViewArtist);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DisplayDetail.class);
                    context.startActivity(intent);
                }
            });
        }

    }
}