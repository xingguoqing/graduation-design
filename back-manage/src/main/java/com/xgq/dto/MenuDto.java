package com.xgq.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/15 上午10:23
 */
@Component
@Setter
@Getter
public class MenuDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("isLeaf")
    private String isLeaf;
    @JsonProperty("pId")
    private Long pId;

}
