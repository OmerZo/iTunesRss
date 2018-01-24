package com.learn.zohky.itunesrss;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GetRssTask extends AsyncTask<Void, Void, Void> {

    private static final String TAG = GetRssTask.class.getSimpleName();

    private Activity activity;
    private ListView lvSongs;
    private String rssUrl;
    private ArrayList<Song> songArrayList;

    public GetRssTask(Activity activity, String rssUrl){
        this.activity = activity;
        this.rssUrl = rssUrl;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.lvSongs = (ListView)activity.findViewById(R.id.lvSongs);
        this.songArrayList = new ArrayList<Song>();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String restCall = getRestCall();
        System.out.println("Rest CAll : " + restCall);
        ParserJson(restCall);
        return null;
    }

    private String getRestCall() {
        String result = "";
        try{
//            String urlStr = "https://rss.itunes.apple.com/api/v1/us/apple-music/hot-tracks/all/10/explicit.json";
            URL url = new URL(rssUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder sBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                sBuilder.append(line).append("\n");
            }
            connection.disconnect();
            in.close();
            result = sBuilder.toString();
        } catch (Exception e){
            Log.e(TAG,"Exception" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    private void ParserJson(String restCall) {
        try {
            JSONObject jObj = new JSONObject(restCall);
            JSONObject jObj2 = jObj.getJSONObject("feed");
            JSONArray jArray = jObj2.getJSONArray("results");
            for (int i = 0; i < jArray.length(); i++) {
                Song song = new Song();
                JSONObject jObj3 = jArray.getJSONObject(i);
                song.setmArtistName(jObj3.getString("artistName"));
                song.setmId(jObj3.getInt("id"));
                song.setmReleaseDate(jObj3.getString("releaseDate"));
                song.setmName(jObj3.getString("name"));
                song.setmArtworkUrl(jObj3.getString("artworkUrl100"));
                songArrayList.add(song);
            }
        } catch (Exception e){
            Log.e(TAG,"Exception" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        SongAdapter songAdapter = new SongAdapter(activity,R.layout.song_item, songArrayList);
        lvSongs.setAdapter(songAdapter);
    }
}
