package com.example.mabiao.yuekaolianxi;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.mabiao.yuekaolianxi.api.NewsAPI;
import com.example.mabiao.yuekaolianxi.bean.MyBean;
import com.example.mabiao.yuekaolianxi.bean.NewBean;
import com.example.mabiao.yuekaolianxi.fragmnt.Fragment1;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<Fragment> list;
    private List<String> list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initList();
        initXUtils();

    }
    private void initList() {
        list1 = new ArrayList<>();
    }
    private void initXUtils() {
        RequestParams param=new RequestParams(NewsAPI.NEWURL);
     x.http().get(param, new Callback.CommonCallback<String>() {
         @Override
         public void onSuccess(String result) {
             Gson gson=new Gson();
             NewBean newBean = gson.fromJson(result, NewBean.class);
             List<NewBean.DataBean> data = newBean.getData();
             if(data!=null&data.size()>0){
                 for (int i = 0; i <data.size() ; i++) {
                     NewBean.DataBean dataBean = data.get(i);
                     String name = dataBean.getName();
                     System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++"+name);
                     list1.add(name);
                 }
             }
             initData();

         }

         @Override
         public void onError(Throwable ex, boolean isOnCallback) {

         }

         @Override
         public void onCancelled(CancelledException cex) {

         }

         @Override
         public void onFinished() {

         }
     });
    }
    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i <list1.size() ; i++) {

            list.add(new Fragment1());
        }
        //绑定TabLayout和viewpager
        tab.setupWithViewPager(vp);
        MyAdapter mp=new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(mp);
    }
  public class MyAdapter extends FragmentPagerAdapter{

      public MyAdapter(FragmentManager fm) {
          super(fm);
      }
      @Override
      public Fragment getItem(int position) {
          return list.get(position);
      }

      @Override
      public int getCount() {
          return list.size();
      }

      @Override
      public CharSequence getPageTitle(int position) {
          return list1.get(position);
      }
  }

}
