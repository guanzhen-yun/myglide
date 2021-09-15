package com.inke.myglide.glide;

import android.content.Context;
import android.widget.ImageView;

import com.inke.myglide.MD5Utils;

import java.lang.ref.SoftReference;

//图片请求对象封装类
public class BitmapRequest {

    //请求路径
    private String url;
    //上下文
    private Context context;//

    //需要加载图片的控件
    private SoftReference<ImageView> imageView;

    //占位图片
    private int resId;

    //回调对象
    private RequestListener listener;

    //图片的标识
    private String urlMd5;

    public BitmapRequest(Context context) {
        this.context = context;
    }

    //链式调度
    public BitmapRequest load(String url) {
        this.url = url;
        this.urlMd5 = MD5Utils.toMd5(url);
        return this;
    }

    //设置占位图片
    public BitmapRequest loading(int resId) {
        this.resId = resId;
        return this;
    }

    public BitmapRequest listener(RequestListener listener) {
        this.listener = listener;
        return this;
    }

    //显示图片的控件
    public void into(ImageView imageView) {
        imageView.setTag(this.urlMd5);
        this.imageView = new SoftReference<>(imageView);
        RequestManager.getInstance().addBitmapRequest(this);
    }

    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return context;
    }

    public ImageView getImageView() {
        return imageView.get();
    }

    public int getResId() {
        return resId;
    }

    public RequestListener getRequestListener() {
        return listener;
    }

    public String getUrlMd5() {
        return urlMd5;
    }
}
