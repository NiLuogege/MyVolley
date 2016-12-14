package com.example.well.myvolley;

import java.io.Serializable;

public class MissCountResponse implements Serializable {
    public static final int SUCCESS = 0;

    public int errorCode = SUCCESS;

    public String errorMsg = "";

    public String exceptionMsg = "";

    public boolean isSuccess() {
        return errorCode == SUCCESS;
    }
public MissCount result = new MissCount();
}