package com.xgq.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午9:59
 */
@Component
@Setter
@Getter
public class AddrDto {

    private Long id;
    private Long userId;
    private String countryName;
    private String cityName;
    private String partName;
    private String addr;
    private String isDefault;

}
