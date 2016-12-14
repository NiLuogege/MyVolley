package com.example.well.myvolley.netcore;

import java.io.InputStream;

/**
 * Created by Well on 2016/12/14.
 *
 * 访问结果的接口
 */

public interface IHttpListener {

    void onSuccess(InputStream inputStream);

    void onErro();

}
