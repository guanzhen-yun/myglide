package com.inke.myglide.glide;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

public class BitmapDispatcher extends Thread {

    private Handler handler = new Handler(Looper.getMainLooper());

    //定义队列 从头部获取数据 添加 尾巴开始  并发情况下线程安全问题
    private LinkedBlockingQueue<BitmapRequest> requestQueue;

    public BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()) {
            try {
                BitmapRequest br = requestQueue.take();
                //设置占位图片
                showLoadingImage(br);
                //加载图片
                Bitmap bitmap = findBitmap(br);
                //把图片显示到imageView
                showImageView(br, bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void showImageView(BitmapRequest br, Bitmap bitmap) {
        if (bitmap != null && br.getImageView() != null && br.getUrlMd5().equals(br.getImageView().getTag())) {
            ImageView imageView = br.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    if (br.getRequestListener() != null) {
                        RequestListener listener = br.getRequestListener();
                        listener.onSuccess(bitmap);
                    }
                }
            });
        }
    }

    private Bitmap findBitmap(BitmapRequest br) {
        Bitmap bitmap = null;
        bitmap = downloadImage(br.getUrl());
        return bitmap;
    }

    private Bitmap downloadImage(String uri) {
        FileOutputStream fos = null;
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            //创建一个URL对象
            URL url = new URL(uri);
            //然后使用HttpURLConnection通过URL去开始读数据
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private void showLoadingImage(BitmapRequest br) {
        if (br.getResId() > 0 && br.getImageView() != null) {
            final int resId = br.getResId();
            final ImageView iv = br.getImageView();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    iv.setImageResource(resId);
                }
            });
        }
    }
}
