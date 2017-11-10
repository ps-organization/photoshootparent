package com.instrantes.base;

/**
 * Created by Lime on 2017/11/9
 */
public class ApplicationException extends RuntimeException {
    /*错误码*/
    private Integer code;



    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    //此处输出异常代码,此处还未定义
    // 以及异常信息
    @Override
    public String toString() {
        return "ApplicationException{" +
                "code=" + code +
                "message=" + getMessage() +
                '}';
    }
}
