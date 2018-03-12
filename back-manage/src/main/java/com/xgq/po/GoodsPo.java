package com.xgq.po;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/3/9 下午2:34
 */
@Component
@Setter
@Getter
public class GoodsPo {

    private Long id;

    private Long typeId;

    private String goodDesc;

    private String goodName;

    private String goodKey;

    private String pic1Path;

    private String pic2Path;

    private String pic3Path;

    private String pic4Path;

    private String pic5Path;

    private String pic6Path;

    private double costPrice;

    private double groupPrice;

    private double singlePrice;

    private double proxyPrice;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }
}
