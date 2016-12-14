package com.example.well.myvolley.netcore;

/**
 * Created by Well on 2016/12/14.
 * 给调用层用的处理返回数据的接口
 */


public interface IJSonListener<M> {

    void onSuccess(String m);
//    void onSuccess(M m);

    void onErro();
}
