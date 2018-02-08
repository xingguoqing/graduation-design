package com.xgq.errorcode;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/2/8 下午2:51
 */
public enum FileErrorCode implements ICommonCode {


    UPLOAD_FAIL("5001","文件上传失败");

    private String code;

    private String msg;

    FileErrorCode(String code, String msg) {
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
