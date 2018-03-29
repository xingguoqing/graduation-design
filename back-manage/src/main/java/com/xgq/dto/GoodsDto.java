package com.xgq.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author xingguoqing
 * @date 2018/3/9 下午7:22
 */
@Setter
@Getter
public class GoodsDto {

    @NotBlank(message = "{good.goodName.notBlank}")
    private String typeName;

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
