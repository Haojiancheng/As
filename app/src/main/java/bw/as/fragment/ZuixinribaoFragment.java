package bw.as.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import bw.as.HttpUtil;
import bw.as.ImageUtil;
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
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;


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
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(true);
                sendHttpRequest("http://news-at.zhihu.com/api/4/news/latest");
            }
        });
        sendHttpRequest("http://news-at.zhihu.com/api/4/news/latest");

    }

    public void sendHttpRequest(String url) {
        HttpUtil.sendOkHttpRequest(url, new Callback() {


            private ZuixinribaoAdapter zuixinribaoAdapter;
            List<Zuixinribao.StoriesBean> stories;
            private LinearLayoutManager linearLayoutManager;

            @Override
            public void onFailure(Call call, IOException e) {
                //异常处理操作
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //得到服务器返回的具体数据
                stories = new ArrayList<Zuixinribao.StoriesBean>();
                String responseData = response.body().string();
                Zuixinribao zuixinribao = new Gson().fromJson(responseData, Zuixinribao.class);
                stories.addAll(zuixinribao.getStories());
                List<Zuixinribao.TopStoriesBean> top_stories = zuixinribao.getTop_stories();
                zuixinribaoAdapter = new ZuixinribaoAdapter(R.layout.zuixinribao_item, stories);
                final ArrayList<String> imageList = new ArrayList<>();
                final ArrayList<String> titleList = new ArrayList<>();
                for (int i = 0; i < top_stories.size(); i++) {
                    imageList.add(top_stories.get(i).getImage());
                    titleList.add(top_stories.get(i).getTitle());
                }
                linearLayoutManager = new LinearLayoutManager(getActivity());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(false);
                        zuixinRecyclerView.setLayoutManager(linearLayoutManager);
                        zuixinRecyclerView.setAdapter(zuixinribaoAdapter);
                        setBanner();
                    }

                    private void setBanner() {
                        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.zuixinribao_head, null);
                        final Banner banner = inflate.findViewById(R.id.banner);
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                        //设置图片加载器
                        banner.setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                ImageUtil.loderImage((String) path, imageView);
                            }
                        });
                        //设置图片集合
                        banner.setImages(imageList);
                        //设置banner动画效果
                        banner.setBannerAnimation(Transformer.DepthPage);
                        banner.setBannerTitles(titleList);
                        //设置自动轮播，默认为true
                        banner.isAutoPlay(true);
                        //设置轮播时间
                        banner.setDelayTime(1500);
                        //设置指示器位置（当banner模式中有指示器时）
                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        //banner设置方法全部调用完毕时最后调用
                        zuixinribaoAdapter.addHeaderView(inflate);
                        banner.start();
                        zuixinRecyclerView.scrollToPosition(0);
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
