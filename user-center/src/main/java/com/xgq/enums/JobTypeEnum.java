package com.xgq.enums;

import lombok.Getter;

/**
 * @author xingguoqing
 * @date 2018/2/17 下午10:36
 */
@Getter
public enum JobTypeEnum {

    LOCK("1","锁报修"),
    TUBE("2","管道报修"),
    CIRCUIT("3","电路报修");

    private String code;

    private String name;

    JobTypeEnum(String code,String name){
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code){
        for(JobTypeEnum jobTypeEnum:JobTypeEnum.values()){
            if(jobTypeEnum.getCode().equals(code)){
                return jobTypeEnum.getName();
            }
        }
        return null;
    }

}
