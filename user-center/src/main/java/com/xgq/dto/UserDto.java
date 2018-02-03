package com.xgq.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class UserDto {

    private String userCode;

    private String userMail;

    private String userPhone;

    private String userName;

    private String personalProfile;

    private String status;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
