package bw.as.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bw.as.bean.Zhutiribao;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ZhutiribaoAdapter  extends BaseQuickAdapter<Zhutiribao,BaseViewHolder>{
    public ZhutiribaoAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Zhutiribao item) {

    }
}
