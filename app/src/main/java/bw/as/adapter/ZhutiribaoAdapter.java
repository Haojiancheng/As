package bw.as.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import bw.as.ImageUtil;
import bw.as.R;
import bw.as.bean.Zhutiribao;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ZhutiribaoAdapter  extends BaseQuickAdapter<Zhutiribao.OthersBean,BaseViewHolder> {

    public ZhutiribaoAdapter(@LayoutRes int layoutResId, @Nullable List<Zhutiribao.OthersBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Zhutiribao.OthersBean item) {
        helper.setText(R.id.zhuti_item_text,item.getDescription())
                .addOnClickListener(R.id.zhuti_item_image)
        .addOnClickListener(R.id.zhuti_item_text);
        View view = helper.getView(R.id.zhuti_item_image);
        ImageUtil.loderImage(item.getThumbnail(), (ImageView) view);
    }
}
