package com.xgq.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xingguoqing
 * @date 2018/3/21 上午9:25
 */
@Slf4j
@RequestMapping("/foo")
@RestController
public class Test {

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/foo")
    @ResponseBody
    public String foo(@RequestParam String as) {
        ValueOperations<String, String> ops = template.opsForValue();
        String key = "spring.boot.redis.test";
        if(!StringUtils.isEmpty(as)){
            log.info(as);
            ops.set(key, as);
            return as;
        }else{
            return "kong de ";
        }

    }

}
