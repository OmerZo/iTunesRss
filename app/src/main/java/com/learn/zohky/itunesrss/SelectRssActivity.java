package com.learn.zohky.itunesrss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class SelectRssActivity extends AppCompatActivity {

    String temp;
    int resultsLimit;
    Spinner sCountry, sMediaType, sFeedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_rss);

        fillSpinners();

        Button bSelect = (Button)findViewById(R.id.bSelect);
    }

    public void selectClick(View view) {
        EditText etResultsLimit = (EditText)findViewById(R.id.etResultsLimit);
        resultsLimit = Integer.valueOf(etResultsLimit.getText().toString());
        temp = "https://rss.itunes.apple.com/api/v1/" + sCountry.getSelectedItem() + "/" + sMediaType.getSelectedItem() + "/" + sFeedType.getSelectedItem() + "/all/" + resultsLimit + "/explicit.json";
        System.out.println(temp);
        Intent intent = new Intent(this,ShowRssActivity.class);
        intent.putExtra("rssUrl", temp);
        startActivity(intent);
    }

    public void fillSpinners(){
        String [] countryArr = new String[2];
        countryArr[0] = "il";
        countryArr[1] = "us";
        SpinnerAdapter spinnerAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,countryArr);
        sCountry = (Spinner)findViewById(R.id.sCountry);
        sCountry.setAdapter(spinnerAdapter);

        String [] mediaTypeArr = new String[1];
        mediaTypeArr[0] = "apple-music";
//        mediaTypeArr[1] = "itunes-music";
//        mediaTypeArr[2] = "tv-shows";
//        mediaTypeArr[3] = "movies";
//        mediaTypeArr[4] = "podcasts";
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, mediaTypeArr);
        sMediaType = (Spinner)findViewById(R.id.sMediaType);
        sMediaType.setAdapter(spinnerAdapter);

        String [] feedTypeArr = new String[4];
        feedTypeArr[0] = "hot-tracks";
        feedTypeArr[1] = "new-releases";
        feedTypeArr[2] = "top-albums";
        feedTypeArr[3] = "top-songs";
        spinnerAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, feedTypeArr);
        sFeedType = (Spinner)findViewById(R.id.sFeedType);
        sFeedType.setAdapter(spinnerAdapter);
    }
}
