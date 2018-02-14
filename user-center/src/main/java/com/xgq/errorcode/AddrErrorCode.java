package com.xgq.errorcode;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午10:13
 */
@Getter
public enum AddrErrorCode implements ICommonCode {

    ADDR_NOT_EXIST("5001","地址不存在"),
    COUNTRY_NOT_EXIST("5002","所选国家不存在"),
    CITY_NOT_EXIST("5003","所选城市不存在"),
    COUNTRY_CITY_NOT_MACH("5004","国家和城市不匹配")
    ;

    private String code;

    private String msg;

    AddrErrorCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
