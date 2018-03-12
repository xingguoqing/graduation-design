package com.xgq.errorcode;

import lombok.Getter;
import responsecode.ICommonCode;

/**
 * @author xingguoqing
 * @date 2018/3/9 下午2:50
 */
@Getter
public enum GoodsErrorCode implements ICommonCode {


    GOOD_TYPE_UNLEGAL("G001","商品分类不合法"),
    GOOD_NAME_UNLEGAL("G002","商品名称不合法"),
    GOOD_KEY_UNLEGAL("G003","商品搜索关键字不合法"),
    GOOD_COST_PRICE_UNLEGAL("G004","商品成本价不合法"),
    GOOD_GROUP_PRICE_UNLEGAL("G005","商品团购价不合法"),
    GOOD_PROXY_PRICE_UNLEGAL("G006","商品代理价不合法"),
    GOOD_SINGLE_PRICE_UNLEGAL("G007","商品销售价不合法"),
    GOOD_HAS_EXIST("G008","商品已存在"),
    GOOD_NOT_EXIST("G009","商品不存在")
    ;

    private String code;
    private String msg;

    GoodsErrorCode(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
