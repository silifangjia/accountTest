package com.test;

import com.AccountApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =AccountApplication.class)
public class Test {
    public static void main(String[] args) {
        Account a1 = new Account(1.5,1122,1000,"George");

        a1.setAnnuallnterestRate(5.5);

        a1.deposit(30);

        a1.deposit(40);

        a1.deposit(50);

        a1.withDraw(5);

        a1.withDraw(4);

        a1.withDraw(2);

        a1.output();

    }
}