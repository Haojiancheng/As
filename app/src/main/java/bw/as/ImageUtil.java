package bw.as;


import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by Administrator on 2017/9/22.
 */

public class ImageUtil {
    public static void loderImage(String url,ImageView imageView){
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(imageView.getContext());

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader instance = ImageLoader.getInstance();
        instance.init(configuration);
        instance.displayImage(url, imageView ,options);
    }
}
