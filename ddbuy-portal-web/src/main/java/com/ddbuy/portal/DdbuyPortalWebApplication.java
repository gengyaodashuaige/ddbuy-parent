package com.ddbuy.portal;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration  //启动类
public class DdbuyPortalWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdbuyPortalWebApplication.class, args);
    }

}
