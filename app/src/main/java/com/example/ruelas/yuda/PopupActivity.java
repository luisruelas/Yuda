package com.example.ruelas.yuda;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URI;

/**
 * Created by Ruelas on 16/02/2017.
 */
public class PopupActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener {
    MediaController mc;
    RowCatalogo row;
    TextView tvdescripcion;
    TextView tvnombre;
    VideoView vvvideo;
    LinearLayout llvideowrapper;
    LinearLayout llmainWrapper;
    String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupvideo);
        llmainWrapper= (LinearLayout) findViewById(R.id.llmainwrapper);
        llmainWrapper.setOnClickListener(this);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        llvideowrapper= (LinearLayout) findViewById(R.id.llvideowrapper);
        tvdescripcion= (TextView) findViewById(R.id.tvdescripcion);
        tvnombre= (TextView) findViewById(R.id.tvnombreproductpu);
        vvvideo= (VideoView) findViewById(R.id.vvvideo);
        if(getIntent().getExtras()!=null){
           row= (RowCatalogo)getIntent().getExtras().getSerializable("row");
            if(row.getDescripcion().equals("tutorial")){
                tvdescripcion.setText("Tutorial del juego '"+row.getNombre()+"'");
                //setvideotutorial
                url=AllProducts.getUrlvideos()+row.getCodigo()+"t"+AllProducts.getExtensionvideos();
            }
            else{
                //setvideonormal
                tvdescripcion.setText(row.getDescripcion());
                url=AllProducts.getUrlvideos()+row.getCodigo()+AllProducts.getExtensionvideos();
            }
            tvnombre.setText(row.getNombre());
            //Toast.makeText(this,row.getDescripcion(),Toast.LENGTH_SHORT);
        }

        //Uri urlvideo= Uri.parse(url);
        Uri urlvideo=Uri.parse(url);
        mc=new MediaController(this);
        vvvideo.setVideoURI(urlvideo);
        vvvideo.setBackground(new ColorDrawable(Color.RED));
        vvvideo.setOnPreparedListener(this);
        vvvideo.setMediaController(mc);
        //vvvideo.requestFocus();
        //mc.setAnchorView(llvideowrapper);
        mc.setMediaPlayer(vvvideo);
        mc.setX(vvvideo.getX());
        vvvideo.start();
        mc.show();
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(0,0);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.llmainwrapper:
                finish();
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        vvvideo.setBackground(new ColorDrawable(Color.TRANSPARENT));
        Thread t=new Thread(){
            @Override
            public void run() {
                try {
                    vvvideo.start();
                    this.sleep(10);
                    vvvideo.pause();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.run();
    }
}
