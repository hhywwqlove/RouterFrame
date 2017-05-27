package com.router.android.model;

/**
 * Created by WeiqiangWang on 17/5/10.
 */

public class RouterResult<T> {
    private String fullUri;
    private int respCode;
    private String errorMsg;
    private T data;

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getFullUri() {
        return fullUri;
    }

    public void setFullUri(String fullUri) {
        this.fullUri = fullUri;
    }

    public RouterResult(String fullUri, int respCode, String errorMsg) {
        this.fullUri = fullUri;
        this.respCode = respCode;
        this.errorMsg = errorMsg;
    }

    public RouterResult(String fullUri, int respCode, String errorMsg, T data) {
        this.fullUri = fullUri;
        this.respCode = respCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }
}
