package bw.as;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import bw.as.adapter.XiangqingAdapter;
import bw.as.bean.More;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/22.
 */

public class XiangqingActivity extends AppCompatActivity {
    @BindView(R.id.xiangqing_recycler)
    RecyclerView xiangqingRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int anInt = intent.getIntExtra("int", 11);
        sendHttpRequest("http://news-at.zhihu.com/api/4/theme/"+anInt);
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
                        More more = new Gson().fromJson(responseData, More.class);
                        List<More.StoriesBean> stories = more.getStories();
                        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XiangqingActivity.this);
                        final XiangqingAdapter xiangqingAdapter = new XiangqingAdapter(R.layout.xiangqing_item,stories);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                xiangqingRecycler.setLayoutManager(linearLayoutManager);
                                xiangqingRecycler.setAdapter(xiangqingAdapter);
                            }
                        });
                    }
                });
            }

}
