package com.learn.zohky.itunesrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowRssActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rss);

        Bundle extras = getIntent().getExtras();
        String rssUrl = (String) extras.get("rssUrl");
        new GetRssTask(this, rssUrl).execute();
    }
}
