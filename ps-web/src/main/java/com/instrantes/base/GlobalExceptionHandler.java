package com.instrantes.base;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Lime on 2017/11/9
 */
//只能配置程序中抛出的错误
@ControllerAdvice(basePackages = "com.instrantes")
public class GlobalExceptionHandler {

    //全局类定义异常
        @ExceptionHandler(value = { ApplicationException.class , RuntimeException.class })
            public String exception(Exception exception ) {
            return "全局定义异常类：出现异常";
        }

//    @ExceptionHandler(value = { IOException.class , RuntimeException.class })
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String exception(Exception exception ) {
//        logger.info("Catch an exception", exception);
//        return  new ModelAndView("error/errorPage");
//    }
}
