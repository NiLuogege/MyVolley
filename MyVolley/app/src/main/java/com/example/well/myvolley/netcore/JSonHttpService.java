package com.example.well.myvolley.netcore;

import android.util.Log;

import com.example.well.myvolley.BuildConfig;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Well on 2016/12/14.
 * 具体的实现网络请求的类
 */

public class JSonHttpService implements IHttpService {
    IHttpListener mIHttpListener;
    private HttpClient mHttpClient = new DefaultHttpClient();
    String url;
    private byte[] requestData;
    private HttpRequestBase mHttpRequestBase;
    private HttpResponcehandle mHttpResponcehandle = new HttpResponcehandle();

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    @Override
    public void excute() {
//        this.mHttpRequestBase = new HttpPost(url);
//        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(requestData);
//        ((HttpPost) mHttpRequestBase).setEntity(byteArrayEntity);
//        try {
//
//            this.mHttpClient.execute(mHttpRequestBase, mHttpResponcehandle);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        if (BuildConfig.DEBUG) Log.e("excute", "excute");
        this.mHttpRequestBase =new HttpGet("http://www.ccholterserver.com:9093/public/get_miss_msg_count/user_id/104");
        try {
            mHttpClient.execute(mHttpRequestBase,mHttpResponcehandle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setHttpCallBack(IHttpListener httpListener) {
        this.mIHttpListener = httpListener;
    }

    private class HttpResponcehandle extends BasicResponseHandler {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException {
            int code = response.getStatusLine().getStatusCode();
            if (BuildConfig.DEBUG) Log.e("code", "code="+code);
            if (code == 200) {
                HttpEntity httpEntity = response.getEntity();
                if (mIHttpListener != null) {
                    try {
                        InputStream inputStream = httpEntity.getContent();
                        mIHttpListener.onSuccess(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (mIHttpListener != null) {
                    mIHttpListener.onErro();
                }
            }

            return null;
        }
    }


}
