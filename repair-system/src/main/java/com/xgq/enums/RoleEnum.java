package com.xgq.enums;

import com.xgq.errorcode.RoleErrorCode;
import lombok.Getter;
import util.exception.BusinessRuntimeException;

/**
 * @author xingguoqing
 * @date 2018/2/13 下午9:23
 */

public enum RoleEnum {

    ORDINARY_USER(1L,"普通🈷️用户"),
    ADMINISTRATORS(2L,"管理员"),
    REPAIR_PERSONNEL(3L,"维修人员");

    private Long code;

    private String name;

    RoleEnum(Long code,String name){
        this.code = code;
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static RoleEnum getEnumByCode(Long code){
        for(RoleEnum roleEnum : RoleEnum.values()){
            if(roleEnum.getCode().equals(code)){
                return roleEnum;
            }
        }
        BusinessRuntimeException.wrapBusiException(RoleErrorCode.ROLE_ILLEGAL);
        return null;
    }

}
