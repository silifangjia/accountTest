package com.account.controller;

import com.account.pojo.Account;
import com.account.service.AccountService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@RequestMapping("/web/account/")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;


    /**
     * 检验参数是否可用
     * @param data
     * @param type
     * @return
     * */
    @GetMapping("check/{data}/{type}")
    public ResponseEntity<Boolean> checkUserData(@PathVariable("data")String data, @PathVariable(value = "type")Integer type){


        Boolean boo = this.userService.checkData(data,type);
        if(boo == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(boo);
    }




    /**
     * 注册
     * @param user
     * @param code
     * @return
     * */
    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid User user, @RequestParam("code")String code){
        Boolean boo = this.userService.register(user,code);
        if(boo==null||boo==false){{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }}
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 根据用户名和id查询用户
     * @param username
     * @param password
     * @return
     * */
    @GetMapping("query")
    public ResponseEntity<User> queryUser(
            @RequestParam("username")String username,
            @RequestParam("id")String id
    ){
        User user = this.userService.queryUser(username,id);
        if(user==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }


    /**
     * 存钱
     */
    @PostMapping("deposit")
    public void deposit(@RequestParam("amount") double amount) {
        accountService.deposit(amount);
    }

    /**
     * 取钱
     */
    @PostMapping("withDraw")
    public void withDraw(@RequestParam("amount") double amount) {
        accountService.withDraw(amount);
    }
    /**
     * 查询月利息
     */
    @GetMapping("getMonthlyInterest")
    public double getMonthlyInterest() {
        return (balance * annuallnterestRate / 12) / 100;

    }

}
