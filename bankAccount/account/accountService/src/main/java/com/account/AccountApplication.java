package com.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


@EnableDiscoveryClient
@MapperScan(value = "com.account.mapper")
@SpringBootApplication
public class AccountApplication {

    public static void main(String[] args) {

        SpringApplication.run(AccountApplication.class,args);
    }
}
