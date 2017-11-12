package com.instrantes.base;


import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 集中处理类异常
 *
 * @ControllerAdvice：注解将作用在所有注解了@RequestMapping的控制器的方法上。
 * @ExceptionHandler：用于全局处理控制器里的异常。
 * Created by Lime on 2017/11/9
 */

//只能配置程序中抛出的错误
@ControllerAdvice(basePackages = "com.instrantes")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final static Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleDataNotFoundException(IllegalStateException ex, WebRequest request) throws IOException {
//        WebReturnCode webReturnCode=new WebReturnCode(0, "failed");

        return new ResponseEntity<Object>(ex, new HttpHeaders(), HttpStatus.valueOf("failed"));
    }



    /**
     * 根据各种异常构建 ResponseEntity 实体. 服务于以上各种异常
     */
//    private ResponseEntity<Object> getResponseEntity(RuntimeException ex, WebRequest request, ReturnCode returnCode) {
////        ReturnTemplate returnTemplate = new ReturnTemplate();
////        returnTemplate.setStatusCode(specificException);
////        returnTemplate.setErrorMsg(ex.getMessage());
//        return handleExceptionInternal(ex,returnCode.getCode(),
//                new HttpHeaders(), HttpStatus.OK, request);
//    }

    protected <T> ResponseEntity<T> response(T body, HttpStatus status) {
        log.debug("Responding with a status of {}", status);
        return new ResponseEntity<>(body, new HttpHeaders(), status);
    }

    //    //全局类定义异常,此处是样例
//    @ExceptionHandler(value = {IllegalStateException.class})
//    public String appException(IllegalStateException illegalStateException) {
//        System.out.println(illegalStateException.toString());
//        illegalStateException.printStackTrace();
//        return "异常声明，请 求页面不存在";
//    }


    @ExceptionHandler(value = {ApplicationException.class})
    public String appException(ApplicationException applicationException) {
        System.out.println(applicationException.toString());
        applicationException.printStackTrace();
        return "异常声明，请求页面不存在";
    }


//    @ExceptionHandler(value = {RuntimeException.class, ApplicationException.class})
//        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String cppException(IllegalStateException illegalStateException) {
//        System.out.println(illegalStateException.toString());
//        illegalStateException.printStackTrace();
//        return "异常声明，请求页面不存在";
//    }


//
//    @ExceptionHandler(ApplicationException.class)
//    @ResponseBody
//    public void handleBizExp(HttpServletRequest request, Exception ex) {
//        log.info("Business exception handler  " + ex.getMessage());
//        request.getSession(true).setAttribute(EXPTION_MSG_KEY, ex.getMessage());
//    }

//    @ResponseBody
//    private void outputMessage(HttpServletResponse response, long errCode, String errMsg) throws IOException {
//        ReturnCode result = ReturnCode(errCode, errMsg);
//        String json = JSON.toJSONString(result);
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/json");
//        ServletOutputStream os = response.getOutputStream();
//        os.write(json.getBytes("utf-8"));
//    }

}
