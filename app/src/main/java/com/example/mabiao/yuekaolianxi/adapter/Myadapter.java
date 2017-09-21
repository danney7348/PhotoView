package com.example.mabiao.yuekaolianxi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.mabiao.yuekaolianxi.HomeActivity;
import com.example.mabiao.yuekaolianxi.R;
import com.example.mabiao.yuekaolianxi.bean.JsonBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;
import java.util.List;

import cn.bluemobi.dylan.photoview.library.PhotoView;

/**
 * Created by mabiao on 2017/9/21.
 */

public class Myadapter extends BaseAdapter{
    private Context context;
    private List<JsonBean> list;

    public Myadapter(Context context, List<JsonBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v= LayoutInflater.from(context).inflate(R.layout.fragmentitem, null);
        ImageView ph = v.findViewById(R.id.ph);
        ImageLoader.getInstance().displayImage(list.get(i).avatar_url,ph);
        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,HomeActivity.class);
                intent.putExtra("url", (Serializable) list);
                context.startActivity(intent);
            }
        });
        return v;
    }
}
