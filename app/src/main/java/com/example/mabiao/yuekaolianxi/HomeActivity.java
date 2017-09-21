package com.example.mabiao.yuekaolianxi;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.mabiao.yuekaolianxi.bean.JsonBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.List;

import cn.bluemobi.dylan.photoview.library.PhotoView;

public class HomeActivity extends AppCompatActivity {

    private List<JsonBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewPager wv = (ViewPager) findViewById(R.id.vp);
        Intent intent=getIntent();
        list = (List<JsonBean>) intent.getSerializableExtra("url");
        wv.setAdapter(new Myadapter());

    }
    public class Myadapter extends PagerAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView pv=new PhotoView(HomeActivity.this);
            ImageLoader.getInstance().displayImage(list.get(position).avatar_url,pv);
            container.addView(pv);
            return pv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
