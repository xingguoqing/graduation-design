package com.xgq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xingguoqing
 * @date 2018/2/8 下午2:42
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class,args);
    }
}
