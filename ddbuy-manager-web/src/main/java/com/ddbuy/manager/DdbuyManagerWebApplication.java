package com.ddbuy.manager;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration  //启动dubbo
public class DdbuyManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdbuyManagerWebApplication.class, args);
    }

}
