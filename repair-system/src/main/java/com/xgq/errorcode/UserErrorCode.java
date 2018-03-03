package com.xgq.errorcode;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午10:41
 */

public enum UserErrorCode implements ICommonCode{

    NAME_ILLEGAL("4002","用户名不合法"),
    PHONE_ILLEGAL("4004","手机不合法"),
    PASSWORD_ILLEGAL("4005","密码不合法"),
    PHONE_TAKE_UP("4006","手机号已被使用"),
    LOGIN_ILLEGAL("4001","手机号或密码不正确"),
    USER_NOT_EXIST("4003","用户不存在"),
    USER_NOT_ACTIVATION("4007","用户未激活，不允许登录")
    ;

    private String code;

    private String msg;

    UserErrorCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
