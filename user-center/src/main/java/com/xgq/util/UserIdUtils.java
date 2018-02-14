package com.xgq.util;

import jwt.JwtUtils;
import util.HttpContextUtils;

/**
 * @author xingguoqing
 * @date 2018/2/14 下午2:57
 */
public class UserIdUtils {



    public static Long getUserId(){
        String sign = HttpContextUtils.getHttpServletRequest().getHeader("sign");
        return Long.valueOf(JwtUtils.parseJWT(sign));
    }

}
