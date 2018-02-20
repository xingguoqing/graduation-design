package com.xgq.enums;

import lombok.Getter;

/**
 * @author xingguoqing
 * @date 2018/2/17 下午10:43
 */
@Getter
public enum JobStatusEnum {
    SUB("0", "已提交"), ACTION("1", "处理中"), OVER("2", "处理完成");

    private String code;
    private String name;

    JobStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (JobStatusEnum jobStatusEnum : JobStatusEnum.values()) {
            if(jobStatusEnum.getCode().equals(code)){
                return jobStatusEnum.getName();
            }
        }
        return null;
    }
}
