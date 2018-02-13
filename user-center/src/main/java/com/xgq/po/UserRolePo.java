package com.xgq.po;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author xingguoqing
 * @date 2018/2/13 下午9:29
 */
@Component
@Setter
@Getter
public class UserRolePo {

    private Long id;

    private Long userId;

    private Long roleId;

    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
