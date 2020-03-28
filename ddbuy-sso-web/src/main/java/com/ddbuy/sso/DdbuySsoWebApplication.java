package com.ddbuy.sso;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration  //启动类
public class DdbuySsoWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdbuySsoWebApplication.class, args);
    }

}
