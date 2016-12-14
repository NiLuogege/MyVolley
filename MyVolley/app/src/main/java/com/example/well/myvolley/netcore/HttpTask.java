package com.example.well.myvolley.netcore;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.well.myvolley.BuildConfig;

import java.io.Serializable;

/**
 * Created by Well on 2016/12/14.
 */

public class HttpTask<T> implements Runnable {

    private IHttpService mIHttpService;

    public <T extends Serializable> HttpTask(T requestInfo, String url, IHttpListener httpListener) {

        this.mIHttpService = new JSonHttpService();//这里就是使用了策略模式,需要什么httpService就New什么就行了
        mIHttpService.setUrl(url);
        mIHttpService.setHttpCallBack(httpListener);

        String requestContent = JSON.toJSONString(requestInfo);
        if (BuildConfig.DEBUG) Log.e("requestContent", requestContent);
        try {
            mIHttpService.setRequestData(requestContent.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.mIHttpService.excute();
    }
}
