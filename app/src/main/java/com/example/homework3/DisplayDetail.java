package com.example.homework3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DisplayDetail extends MainActivity {
    ArrayList<MusicList> items = new ArrayList<>();
    Bundle bundle = new Bundle();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_list);
        bundle = getIntent().getBundleExtra("bundle");
        MusicList music = (MusicList) bundle.getSerializable("music");
        if(music!=null)Log.d("items:", music.toString());

        TextView textViewGenre = (TextView)findViewById(R.id.textViewGenre);
        TextView textViewArtist = (TextView)findViewById(R.id.textViewArtist);
        TextView textViewAlbum = (TextView)findViewById(R.id.textViewAlbum);
        TextView textViewAlbumP = (TextView)findViewById(R.id.textViewAlbumP);
        TextView textViewTrackP = (TextView)findViewById(R.id.textViewTrackP);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        textViewGenre.setText("Genre: "+ music.getPrimaryGenreName());
        textViewArtist.setText("Artist: "+ music.getArtistName());
        textViewAlbum.setText("Album: "+ music.getCollectionName());
        textViewAlbumP.setText("Track Price: "+ music.getTrackPrice() + " $");
        textViewTrackP.setText("Album Price: "+ music.getCollectionPrice()+ " $");
        Picasso.get().load(music.getartworkUrl100()).into(imageView);
        Button b = (Button) findViewById(R.id.exitbtn);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
