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
        ImageView ivSongArt = (ImageView)convertView.findViewById(R.id.ivSongArt);
        MainActivity mainActivity = (MainActivity) parent.getContext();
//        ImageView ivTest = (ImageView)mainActivity.findViewById(R.id.ivTest);
//        new DownLoadImageTask(ivTest).execute(song.getmArtworkUrl()); Not working!!
        Picasso.with(mainActivity).load("http://is3.mzstatic.com/image/thumb/Music118/v4/b4/ab/ad/b4abadbc-36eb-86a6-94fb-4fa9f404e66c/source/200x200bb.png").into(ivSongArt);
        return convertView;
    }
}
