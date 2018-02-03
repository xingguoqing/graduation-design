package com.xgq.po;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class UserPo {

    private Integer userId;

    private String userCode;

    private String userPassword;

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
