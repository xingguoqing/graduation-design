package com.xgq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xingguoqing
 * @date 2018/1/27 下午5:39
 */
@SpringBootApplication
@MapperScan(value = "com.xgq.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
