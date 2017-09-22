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
import bw.as.bean.Zuixinribao;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ZuixinribaoAdapter extends BaseQuickAdapter<Zuixinribao.StoriesBean,BaseViewHolder> {

    public ZuixinribaoAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Zuixinribao.StoriesBean item) {
        View view = helper.getView(R.id.zuixin_item_image);
        helper.setText(R.id.zuixin_item_text,item.getTitle());
        ImageUtil.loderImage(item.getImages().get(0), (ImageView) view);
    }
}
