package com.xgq.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/3/12 下午2:49
 */
@Component
@Setter
@Getter
public class GoodPicDto {

    private String goodName;

    private String goodKey;

    private String pic1Path;

    private String pic2Path;

    private String pic3Path;

    private String pic4Path;

    private String pic5Path;

    private String pic6Path;
}
