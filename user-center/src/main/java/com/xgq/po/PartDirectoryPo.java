package com.xgq.po;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/14 上午10:35
 */
@Component
@Setter
@Getter
public class PartDirectoryPo {

    private Long id;
    private String name;
    private String code;
    private String isLeaf;
    private String parentCode;
}
