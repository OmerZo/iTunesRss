package com.learn.zohky.itunesrss;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SongAdapter extends ArrayAdapter<Song>{

    List<Song> mSongList;
    LayoutInflater layoutInflater;

    public SongAdapter(@NonNull Context context, int resource, @NonNull List<Song> songList) {
        super(context, resource, songList);
        this.mSongList = songList;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.song_item, parent, false);
            holder = new ViewHolder();
            holder.tvSongName = (TextView)convertView.findViewById(R.id.tvItemName);
            holder.ivSongArt = (ImageView)convertView.findViewById(R.id.ivSongArt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Song song = (Song) mSongList.get(position);
        holder.tvSongName.setText(song.getmName());

        if(holder.ivSongArt != null) {
            MainActivity mainActivity = (MainActivity) parent.getContext();
//            new DownLoadImageTask(holder.ivSongArt).execute(song.getmArtworkUrl()); Less efficient (not handle cache)
            Picasso.with(mainActivity).load(song.getmArtworkUrl()).into(holder.ivSongArt);
        }
        return convertView;
    }

    private static class ViewHolder{
        TextView tvSongName;
        ImageView ivSongArt;
    }
}
