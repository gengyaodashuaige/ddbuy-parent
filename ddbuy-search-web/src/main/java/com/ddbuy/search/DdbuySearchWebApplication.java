package com.ddbuy.search;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration  //启动类
public class DdbuySearchWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdbuySearchWebApplication.class, args);
    }

}
