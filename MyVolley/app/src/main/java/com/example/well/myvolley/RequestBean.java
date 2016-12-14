package com.example.well.myvolley;

import java.io.Serializable;

/**
 * Created by Well on 2016/12/14.
 */

public class RequestBean implements Serializable{
    public String user_id;

    public RequestBean(String user_id) {
        this.user_id = user_id;
    }
}
