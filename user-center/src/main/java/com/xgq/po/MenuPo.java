package com.xgq.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:20
 */
@Component
@Setter
@Getter
public class MenuPo {

    private Long id;
    private String menuName;
    private String menuUrl;
    private String isLeaf;
    private Long parentId;
}
