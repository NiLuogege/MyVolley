package com.example.well.myvolley.netcore;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.well.myvolley.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Well on 2016/12/14.
 * <p>
 * 真实的处理json数据的类
 */

public class JSonDealListener<M> implements IHttpListener {
    private Class<M> reponceClass;//接收的对象
    private IJSonListener<M> mIJSonListener;

    Handler mHandler = new Handler(Looper.getMainLooper());//使用主线程的looper将消息切换到主线程

    public JSonDealListener(Class<M> reponceClass, IJSonListener<M> ijSonListener) {
        this.reponceClass = reponceClass;
        mIJSonListener = ijSonListener;
    }



    @Override
    public void onSuccess(InputStream inputStream) {
        final String content = getContent(inputStream);

//        Gson gson = new Gson();
//        final M responese = gson.fromJson(content, reponceClass);
////        final M responese = JSON.parseObject(content, reponceClass);
//        if (BuildConfig.DEBUG) Log.e("JSonDealListener", "mHandler:" + mHandler);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (BuildConfig.DEBUG) Log.e("JSonDealListener", "进来了");
                if (mIJSonListener != null) {
                    mIJSonListener.onSuccess(content);
                }
            }
        });
    }

    private String getContent(InputStream inputStream) {
        String content = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        content = sb.toString();
        if (BuildConfig.DEBUG) Log.e("JSonDealListener", content);
        return content;
    }

    @Override
    public void onErro() {

    }
}
