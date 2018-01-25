package com.learn.zohky.itunesrss;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ShowRssActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rss);

        Bundle extras = getIntent().getExtras();
        String rssUrl = (String) extras.get("rssUrl");
        new GetRssTask(this, rssUrl).execute();
        ListView lvSongs = (ListView)findViewById(R.id.lvSongs);
        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvItemName = (TextView) view.findViewById(R.id.tvItemName);
                youtubeSearch(tvItemName.getText().toString());
            }
        });

        lvSongs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvItemName = (TextView) view.findViewById(R.id.tvItemName);
                copyToClip(tvItemName.getText().toString());
                EditText etNoteAdrr = (EditText)findViewById(R.id.etNoteAdrr);
                note(etNoteAdrr.getText().toString());
                return true;
            }
        });
    }

    public void youtubeSearch(String search){
        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.setPackage("com.google.android.youtube");
        intent.putExtra("query", search);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void copyToClip(String save){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Text", save);
        clipboard.setPrimaryClip(clip);
    }

    public void note(String noteAdrr){
        Intent intent = new Intent();
        String uri = noteAdrr;
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(uri));
        intent.setPackage("com.google.android.keep");
        startActivity(intent);
    }
}
