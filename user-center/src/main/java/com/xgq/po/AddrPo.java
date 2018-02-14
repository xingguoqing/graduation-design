package com.xgq.po;

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
public class AddrPo {

    private Long id;
    private Long userId;
    private Long countryId;
    private Long cityId;
    private String addr;
    private String isDefault;

}
