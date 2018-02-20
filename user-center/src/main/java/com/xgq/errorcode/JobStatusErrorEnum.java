package com.xgq.errorcode;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/20 下午1:31
 */
@Getter
public enum JobStatusErrorEnum implements ICommonCode {

    ENABLE_ADD_EVAL("7001","任务未完成，不允许添加评论");

    private String code;
    private String msg;


    JobStatusErrorEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
