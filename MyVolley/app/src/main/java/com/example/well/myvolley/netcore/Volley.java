package com.example.well.myvolley.netcore;

import android.util.Log;

import com.example.well.myvolley.BuildConfig;

import java.io.Serializable;
import java.util.concurrent.FutureTask;

/**
 * Created by Well on 2016/12/14.
 * <p>
 * 用来使调用层和框架层进行分离的
 */

public class Volley {

    public static <T extends Serializable, M> void
    sendRequest(T requestInfo, String url, Class<M> reponce, IJSonListener<M> httpCallable) {//T : 调用类型  M: 响应类型

        IHttpListener httpListener = new JSonDealListener(reponce, httpCallable);

        HttpTask<T> httpTask = new HttpTask<T>(requestInfo, url, httpListener);

        if (BuildConfig.DEBUG) Log.e("Volley", "Volley");
        ThreadPoolManager.getInstance().execute(new FutureTask<Object>(httpTask,null));
    }
}
