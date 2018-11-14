package com.example.ruelas.yuda;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Ruelas on 02/03/2017.
 */
public class setImageAsync extends AsyncTask<Void,Void,Void> {
    private String url;
    private ImageView iv;
    public setImageAsync(String url, ImageView iv) {
        this.iv=iv;
        this.url=url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        ImageLoader.getInstance().displayImage(url,iv);
    }

}
