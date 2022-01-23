package com.leyou.user.controller;

import com.leyou.user.pojo.User;
import com.leyou.user.service.UserService;
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


/**
 * Created by IntelliJ IDEA
 *
 * @author: Lu Yujie
 * on2018/11/1
 * 21:44
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;


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
     * 发送手机验证码
     * @param
     * @return
     * 改成GetMapping测试结果ok (返回201，只显示阿里大鱼的access key不对)
     * 原本是路径code
     * */
    @PostMapping("send")
    public ResponseEntity<Void> sendVerifyCode(@RequestParam("phone") String phone){

        Boolean boo = this.userService.sendVerifyCode(phone);
            if(boo==null||!boo){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
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
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     * */
    @GetMapping("query")
    public ResponseEntity<User> queryUser(
            @RequestParam("username")String username,
            @RequestParam("password")String password
    ){
        User user = this.userService.queryUser(username,password);
        if(user==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(user);
    }

}
