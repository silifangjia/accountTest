package com.auth2.service;

import com.auth2.client.UserClient;
import com.auth2.entity.UserInfo;
import com.auth2.config.JwtProperties;
import com.auth2.utils.JwtUtils;
import com.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;


@Service
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties properties;

    public String authentication(String username, String password) {

        try {
            //调用微服务
            User user = this.userClient.queryUser(username,password);

            //如果查询结果为null,则直接返回null
            if(user==null){
                return null;
            }
            //如果有查询结果,则生成token
            String token = JwtUtils.generateToken(new UserInfo(user.getId(),user.getUsername()),
                    properties.getPrivateKey(),properties.getExpire());
            return token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
