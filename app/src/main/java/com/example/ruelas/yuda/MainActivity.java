package com.example.ruelas.yuda;

import android.graphics.Bitmap;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ViewPager vpmain;
    TabLayout tlmain;
    CustomAdapter camain;
    ImageLoader il;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //deleteDatabase("Juda");
        //imageloader
        DisplayImageOptions defaultOptions= new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config= new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config);
        //inicializar
        tlmain= (TabLayout) findViewById(R.id.tlmain);
        vpmain= (ViewPager) findViewById(R.id.vpmain);
        camain=new CustomAdapter(getSupportFragmentManager());

        //setAdapter y tablayout
        vpmain.setAdapter(camain);
        tlmain.setupWithViewPager(vpmain);
    }
    private class CustomAdapter extends FragmentPagerAdapter{
        ArrayList<cFragment> fragments;
        public CustomAdapter(FragmentManager fm) {
            super(fm);
            fragments=new ArrayList<>();
            fragments.add(new Fragment_catalogo());
            fragments.add(new Fragment_mimagia());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments.get(position).getTitle();
        }
    }
}
