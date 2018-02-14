package com.xgq.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午12:15
 */
@Component
@Setter
@Getter
public class JobDto {

    private Long id;
    private Long subUserId;
    private Long repairerId;
    private String jobType;
    private String jobDesc;
    private String status;
    private String repairerReply;
    private String evaluate;
    private String evaluateLevel;
    private Long addId;


}
