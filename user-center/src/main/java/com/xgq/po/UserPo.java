package com.xgq.po;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

import org.springframework.stereotype.Component;


@Component
@Setter
@Getter
public class UserPo {

    private Long userId;

    private String userPassword;

    private String userPhone;

    private String userName;

    private String userStatus;

    private String createTime;

    private String lastLoginTime;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
