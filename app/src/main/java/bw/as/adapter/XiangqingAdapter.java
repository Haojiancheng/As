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
import bw.as.bean.More;

/**
 * Created by Administrator on 2017/9/22.
 */

public class XiangqingAdapter extends BaseQuickAdapter<More.StoriesBean,BaseViewHolder> {

    public XiangqingAdapter(@LayoutRes int layoutResId, @Nullable List<More.StoriesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, More.StoriesBean item) {
        helper.setText(R.id.xiangqing_item_text,item.getTitle());
        View view = helper.getView(R.id.xiangqing_item_image);
        ImageUtil.loderImage(item.getImages().get(0), (ImageView) view);
    }
}
