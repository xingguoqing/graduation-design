package com.xgq.enums;

import lombok.Getter;

/**
 * @author xingguoqing
 * @date 2018/2/13 下午9:23
 */
@Getter
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

}
