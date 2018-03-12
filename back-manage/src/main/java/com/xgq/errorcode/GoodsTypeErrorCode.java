package com.xgq.errorcode;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/3/5 下午1:42
 */
@Getter
public enum GoodsTypeErrorCode implements ICommonCode {

    NAME_REPEAT("GT001","商品分类名称重复"),
    GOOD_TYPE_NOT_EXIST("GT002","商品分类不存在");

    private String code;
    private String msg;

    GoodsTypeErrorCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

}
