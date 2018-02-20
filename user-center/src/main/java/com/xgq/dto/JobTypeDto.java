package com.xgq.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/20 下午7:40
 */
@Component
@Setter
@Getter
public class JobTypeDto {

    private Long id;
    private String name;
}
