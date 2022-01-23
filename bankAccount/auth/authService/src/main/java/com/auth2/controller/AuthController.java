package com.auth2.controller;

import com.auth2.config.JwtProperties;
import com.auth2.entity.UserInfo;
import com.auth2.service.AuthService;
import com.auth2.utils.JwtUtils;
import com.common.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/web/auth/")
@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties prop;

    /**
     * 登录权限
     * @param username
     * @param password
     * @return
     * */
    @PostMapping("accredit")
    public ResponseEntity<Void> authentication(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ){
        //登录校验
        String token = this.authService.authentication(username,password);
        if(StringUtils.isBlank(token)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        //将token写入cookie,并指定httpOnly为true,防止通过JS获取和修改
        CookieUtils.setCookie(request,response,prop.getCookieName(),token,prop.getCookieMaxAge(),null,true);

        return ResponseEntity.ok().build();

    }

    /**
     * 验证用户信息
     * @param token
     * @return
     * */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyUser(@CookieValue("LY_TOKEN")String token,HttpServletRequest request,HttpServletResponse response){

        try{
            //从token中解析token信息
            UserInfo userInfo = JwtUtils.getInfoFromToken(token,this.prop.getPublicKey());

            //解析成功要重新刷新token
            token = JwtUtils.generateToken(userInfo,this.prop.getPrivateKey(),this.prop.getExpire());

            //更新cookie中的token
            CookieUtils.setCookie(request,response,this.prop.getCookieName(),token,this.prop.getCookieMaxAge());


            //解析成功返回用户信息
            return ResponseEntity.ok(userInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        //出现异常,响应500
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();


    }
}
