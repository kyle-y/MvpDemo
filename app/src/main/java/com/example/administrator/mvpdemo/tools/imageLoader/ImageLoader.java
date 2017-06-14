package com.example.administrator.mvpdemo.tools.imageLoader;

import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.mvpdemo.R;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ImageLoader {

    public static void loadImageWithUrl(ImageView view, String url){
        GlideApp.with(view.getContext())
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.mipmap.ic_launcher)
                .circleCrop()
                .into(view);
    }
}
