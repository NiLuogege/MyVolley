package com.example.well.myvolley.netcore;

/**
 * Created by Well on 2016/12/14.
 * <p>
 * 去请求网络的接口
 */

public interface IHttpService {

    void setUrl(String url); //设置URL

    void excute();  //执行

    void setHttpCallBack(IHttpListener httpListener);

    void setRequestData(byte[] requestData);//设置请求参数
}
