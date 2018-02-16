package com.xgq.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:42
 */
@Component
@Setter
@Getter
public class MenuRolePo {

    private Long id;
    private Long roleId;
    private Long menuId;

}
