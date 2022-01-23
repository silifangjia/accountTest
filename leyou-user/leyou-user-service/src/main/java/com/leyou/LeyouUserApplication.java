package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by IntelliJ IDEA
 *
 * @author: Lu Yujie
 * on2018/11/1
 * 21:38
 */

@EnableDiscoveryClient
@MapperScan(value = "com.leyou.user.mapper")
@SpringBootApplication
public class LeyouUserApplication {

    public static void main(String[] args) {

        SpringApplication.run(LeyouUserApplication.class,args);
    }
}
