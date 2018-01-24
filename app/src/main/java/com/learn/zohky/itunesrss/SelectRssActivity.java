package com.learn.zohky.itunesrss;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import org.w3c.dom.Text;

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

    public void noteClick(View view) {
        copyToClip();
        note();
    }

    public void youtubeSearch(){
        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.setPackage("com.google.android.youtube");
        intent.putExtra("query", "Android");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void youtube(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com"));
        startActivity(intent);
    }

    public void note(){
        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
        //ACTION_SEND  ACTION_SEARCH   ACTION_CREATE_DOCUMENT  ACTION_EDIT  ACTION_INSERT_OR_EDIT
        // ACTION_OPEN_DOCUMENT  ACTION_PASTE  ACTION_PROCESS_TEXT
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://keep.google.com/u/0/#NOTE/1516815827953.351188419"));
        intent.setPackage("com.google.android.keep");
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TITLE,"title");
//        intent.putExtra(Intent.EXTRA_PROCESS_TEXT, "pro");
//        intent.putExtra(Intent.EXTRA_SUBJECT,"subject");
//        intent.putExtra(Intent.EXTRA_TEXT, "text");
//        intent.putExtra(Intent.EXTRA_PROCESS_TEXT, "asdasd");
//        intent.putExtra("note", "asd");
        startActivity(intent);
    }

    private void note2(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT,"subject");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void copyToClip(){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(this.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Text", temp);
        clipboard.setPrimaryClip(clip);
    }


}


//https://keep.google.com/u/0/#LIST/1516815648634.286561972
//https://keep.google.com/u/0/#NOTE/1516815827953.351188419
