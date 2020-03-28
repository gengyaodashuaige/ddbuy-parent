package com.ddbuy.item;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration  //启动类
public class DdbuyItemWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdbuyItemWebApplication.class, args);
    }

}
