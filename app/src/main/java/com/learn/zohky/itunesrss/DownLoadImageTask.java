package com.learn.zohky.itunesrss;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;

public class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>{

    private ImageView mImageView;

    public DownLoadImageTask(ImageView imageView) {
        this.mImageView = imageView;
    }


    @Override
    protected Bitmap doInBackground(String... strings) {
        String imageURL = strings[0];
        Bitmap image = null;
        try {
            InputStream is = new URL(imageURL).openStream();
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e){
            Log.v("Download","catch");
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        mImageView.setImageBitmap(image);
    }
}
