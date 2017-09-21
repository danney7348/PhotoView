package com.example.mabiao.yuekaolianxi.fragmnt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mabiao.yuekaolianxi.MainActivity;
import com.example.mabiao.yuekaolianxi.R;
import com.example.mabiao.yuekaolianxi.adapter.Myadapter;
import com.example.mabiao.yuekaolianxi.bean.JsonBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.bluemobi.dylan.photoview.library.PhotoView;

/**
 * Created by mabiao on 2017/9/20.
 */

public class Fragment1 extends Fragment {

    private View view;
    private String url="http://lf.snssdk.com/neihan/stream/mix/v1/?content_type=-103";
    private List<JsonBean> list;
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.item, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv = view.findViewById(R.id.lv);
        list = new ArrayList<>();
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject js=new JSONObject(result);
                    JSONObject data = js.getJSONObject("data");
                    JSONArray data1 = data.getJSONArray("data");
                    if(data1!=null&data1.length()>0){
                        for (int i = 0; i <data1.length() ; i++) {
                           JSONObject ob = (JSONObject) data1.get(i);
                            JsonBean bean=new JsonBean();
                            JSONObject group = ob.getJSONObject("group");
                            JSONObject user = group.getJSONObject("user");
                            String avatar_url = user.getString("avatar_url");
                            bean.avatar_url=avatar_url;
                            list.add(bean);
                        }
                    }
                    ChaKan();


                } catch (Exception e) {
                    e.printStackTrace();
                }
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

    private void ChaKan() {
        Myadapter mp=new Myadapter(getActivity(),list);
        lv.setAdapter(mp);
    }
}
