package com.instrantes.base;

/**用户信息异常
 * Created by Lime on 2017/11/10
 */
public enum UserReturnCode implements ReturnCode {
    USER_NOT_EXIST(10001, "该账号不存在!"),
    USER_NOT_ALLOW(10002, "该账号不够权限访问!");

    private Integer code;
    private String message;

    UserReturnCode(Integer code, String message) {
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


}
