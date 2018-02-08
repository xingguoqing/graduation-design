package com.xgq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author xingguoqing
 * @date 2018/2/4 下午9:01
 */

@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class,args);
    }

//    @Bean
//    public AuthFilter authFilter(){
//        return new AuthFilter();
//    }
}
