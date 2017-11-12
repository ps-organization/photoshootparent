package com.instrantes.base;

/**
 * 页面请求异常
 * Created by Lime on 2017/11/10
 */
public enum WebReturnCode implements ReturnCode {
    /**
     * 请求失败
     */
    FAILED(0, "failed"),
    /**
     * 请求成功
     */
    SUCCESS(1, "success"),
    /**
     * 请求错误
     */
    UNKNOWN_ERROR(-1, "未知错误,请联系管理员!");
    private Integer code;
    private String message;


    WebReturnCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
