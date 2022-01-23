package com.leyou.auth2.test;

import com.leyou.auth2.entity.UserInfo;
import com.leyou.auth2.utils.JwtUtils;
import com.leyou.auth2.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;


import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by IntelliJ IDEA
 *
 * @author: Lu Yujie
 * on2018/11/2
 * 17:35
 */

public class JwtTest {
    private static final String pubKeyPath = "E:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "E:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }
    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU1OTA1NTA0M30.I_DNMk1WYebZdkEBTzK7r5fgwcWrYXbxqHeriOYp_4ahQzsHYAyrRGWTji_9rqcHNXMMcu96AQrNRUVShofxZeYRHG2RUzG7HJQq2yu0BXr4FPDq2QgDM5i5Am6R5ftsB_xymk13JNLLEOJIZq7MvQ5qwkTnvw9KZh7v9MLEC0U


        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
