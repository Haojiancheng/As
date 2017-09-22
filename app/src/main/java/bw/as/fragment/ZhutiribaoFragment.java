package bw.as.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bw.as.HttpUtil;
import bw.as.R;
import bw.as.XiangqingActivity;
import bw.as.adapter.ZhutiribaoAdapter;
import bw.as.bean.Zhutiribao;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ZhutiribaoFragment extends Fragment {
    @BindView(R.id.zhuti_RecyclerView)
    RecyclerView zhutiRecyclerView;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.zhutiribao_fragment, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sendHttpRequest("http://news-at.zhihu.com/api/4/themes");

    }
    //网络请求操作类，OKHttp
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
                final Zhutiribao zhutiribao = new Gson().fromJson(responseData, Zhutiribao.class);
                List<Zhutiribao.OthersBean> others = zhutiribao.getOthers();
                final ZhutiribaoAdapter zhutiribaoAdapter = new ZhutiribaoAdapter(R.layout.zhutiribao_item,others);
                final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        zhutiRecyclerView.setLayoutManager(gridLayoutManager);
                        zhutiRecyclerView.setAdapter(zhutiribaoAdapter);
                        zhutiribaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(getActivity(), XiangqingActivity.class);
                                intent.putExtra("int",11);
                                startActivity(intent);
                            }
                        });
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
