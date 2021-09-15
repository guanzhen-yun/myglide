package com.inke.myglide.glide;

import java.util.concurrent.LinkedBlockingQueue;

public class RequestManager {

    private static RequestManager requestManager = new RequestManager();

    private RequestManager(){
        start();
    }

    private void start() {
        stop();
        startAllDispatcher();
    }

    private void stop() {
        if(bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
                if(!bitmapDispatcher.isInterrupted()) {
                    bitmapDispatcher.stop();
                }
            }
        }
    }

    private void startAllDispatcher() {
        //创建并开始所有的线程
        //获取手机支持的单个应用最大的线程数
        int threadCount = Runtime.getRuntime().availableProcessors();
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        for (int i = 0; i < threadCount; i++) {
            BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue);
            bitmapDispatcher.start();
            //将每个dispatcher放到数组中，统一处理
            bitmapDispatchers[i] = bitmapDispatcher;
        }
    }

    public static RequestManager getInstance() {
        return requestManager;
    }

    //创建队列
    private LinkedBlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingQueue<>();
    //创建一个线程数组
    private BitmapDispatcher[] bitmapDispatchers;

    //将图片请求加入队列中
    public void addBitmapRequest(BitmapRequest bitmapRequest) {
        if(bitmapRequest == null) {
            return;
        }
        //判断当前请求是否在队列中
        if (!requestQueue.contains(bitmapRequest)) {
            requestQueue.add(bitmapRequest);
        }
    }

}
