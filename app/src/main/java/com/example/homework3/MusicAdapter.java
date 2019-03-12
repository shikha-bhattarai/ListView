package com.example.homework3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<MusicList> {
    public MusicAdapter(Context context, int resource, List<MusicList> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MusicList music = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){ //if no view to re-use then inflate a new one
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.music_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewArtist = (TextView) convertView.findViewById(R.id.textViewArtist);
            viewHolder.textViewPrice = (TextView) convertView.findViewById(R.id.textViewTrackP);
            viewHolder.textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
            viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            convertView.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewArtist.setText("Artist: "+music.artistName);
        viewHolder.textViewDate.setText("Date: "+music.releaseDate);
        viewHolder.textViewPrice.setText("Price: " +String.valueOf(music.trackPrice));
        viewHolder.textViewTitle.setText("Name: "+music.trackName);

        return convertView;
    }

    private static class ViewHolder{
        TextView textViewArtist;
        TextView textViewPrice;
        TextView textViewTitle;
        TextView textViewDate;
    }
}