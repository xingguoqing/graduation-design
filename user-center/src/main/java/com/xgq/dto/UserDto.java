package com.xgq.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class UserDto {

    private Long id;

    private String userPhone;

    private String userName;

    private String userStatus;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
