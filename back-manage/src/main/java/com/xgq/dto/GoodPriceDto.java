package com.xgq.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/3/12 下午1:59
 */
@Component
@Setter
@Getter
public class GoodPriceDto {

    private String goodName;

    private double costPrice;

    private double groupPrice;

    private double singlePrice;

    private double proxyPrice;

}
