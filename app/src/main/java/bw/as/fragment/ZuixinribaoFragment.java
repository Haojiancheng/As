package bw.as.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bw.as.HttpUtil;
import bw.as.R;
import bw.as.adapter.ZuixinribaoAdapter;
import bw.as.bean.Zuixinribao;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ZuixinribaoFragment extends Fragment {
    @BindView(R.id.zuixin_recyclerView)
    RecyclerView zuixinRecyclerView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.zuixinribao_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sendHttpRequest("http://news-at.zhihu.com/api/4/news/latest");
    }

    public void sendHttpRequest(String url){
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //异常处理操作
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到服务器返回的具体数据
                String responseData = response.body().string();
                Zuixinribao zuixinribao = new Gson().fromJson(responseData, Zuixinribao.class);
                List<Zuixinribao.StoriesBean> stories = zuixinribao.getStories();
                final ZuixinribaoAdapter zuixinribaoAdapter = new ZuixinribaoAdapter(R.layout.zuixinribao_item,stories);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        zuixinRecyclerView.setLayoutManager(linearLayoutManager);
                        zuixinRecyclerView.setAdapter(zuixinribaoAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
