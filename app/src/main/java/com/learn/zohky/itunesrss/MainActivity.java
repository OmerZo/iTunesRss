package com.learn.zohky.itunesrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Song> songArrayList = new ArrayList<Song>();

        Song first = new Song("Omer Adam", 1335673721, "2018-01-15", "Shnei Meshugaeim", "http://is3.mzstatic.com/image/thumb/Music118/v4/b4/ab/ad/b4abadbc-36eb-86a6-94fb-4fa9f404e66c/source/200x200bb.png");
        ListView lvSongs = (ListView)findViewById(R.id.lvSongs);

        songArrayList.add(first);

        SongAdapter songAdapter = new SongAdapter(this,R.layout.song_item, songArrayList);

        lvSongs.setAdapter(songAdapter);
    }
}
