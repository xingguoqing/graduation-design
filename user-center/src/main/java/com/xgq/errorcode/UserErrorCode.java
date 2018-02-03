package com.xgq.errorcode;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/3 下午10:41
 */
@Getter
public enum UserErrorCode implements ICommonCode{

    CODE_ILLEGAL("4001","用户编号不合法"),
    NAME_ILLEGAL("4002","用户名不合法"),
    MAIL_ILLEGAL("4003","邮箱不合法"),
    PHONE_ILLEGAL("4004","手机不合法"),
    PASSWORD_ILLEGAL("4005","密码不合法"),
    CODE_TAKE_UP("4101","用户编号已被使用"),
    PHONE_TAKE_UP("4102","手机号已被使用");

    private String code;

    private String msg;

    UserErrorCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
