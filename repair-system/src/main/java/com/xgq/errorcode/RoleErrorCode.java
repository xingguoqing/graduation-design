package com.xgq.errorcode;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午2:26
 */

public enum RoleErrorCode implements ICommonCode {

    ROLE_ILLEGAL("6001","角色不存在");

    private String code;

    private String msg;

    RoleErrorCode(String code,String msg){
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
