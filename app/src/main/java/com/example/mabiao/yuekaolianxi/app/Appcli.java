package com.example.mabiao.yuekaolianxi.app;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by mabiao on 2017/9/20.
 */

public class Appcli extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImag();
        x.Ext.init(this);
    }

    private void initImag() {
        DisplayImageOptions op=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(op)
                .build();
        ImageLoader.getInstance().init(con);
    }
}
