package com.api;

import com.pojo.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface AccountApi {

    @GetMapping("query")
    public Account queryAccount(
            @RequestParam("username") String username,
            @RequestParam("id") String id
    );
}