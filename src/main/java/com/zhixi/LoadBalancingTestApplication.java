package com.zhixi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zhixi.mapper")
@SpringBootApplication
public class LoadBalancingTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoadBalancingTestApplication.class, args);
    }

}
