package com.instrantes.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lime on 2017/11/9
 */

public class ApplicationException extends RuntimeException {
    /*错误码*/

    private Integer code;

    public ApplicationException(Integer code) {
        this.code = code;
    }

    public ApplicationException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public ApplicationException(Throwable cause, Integer code) {
        super(cause);
        this.code = code;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }


    //此处输出异常代码,此处还未定义
    // 以及异常信息
    @Override
    public String toString() {
        return "ApplicationException{" +
                "code=" + code +
                ",message=" + getMessage() +
                '}';
    }
}
