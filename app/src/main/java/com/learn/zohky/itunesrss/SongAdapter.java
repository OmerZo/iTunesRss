package com.learn.zohky.itunesrss;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SongAdapter extends ArrayAdapter<Song>{

    List<Song> mSongList;

    public SongAdapter(@NonNull Context context, int resource, @NonNull List<Song> songList) {
        super(context, resource, songList);
        this.mSongList = songList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Song song = (Song) mSongList.get(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(convertView == null){
            convertView = inflater.inflate(R.layout.song_item, parent, false);
        }

        TextView tvSongName = (TextView)convertView.findViewById(R.id.tvItemName);
        tvSongName.setText(song.getmName());

        return convertView;
    }
}
