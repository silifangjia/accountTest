package com.leyou.auth2.config;

import com.leyou.auth2.utils.RsaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by IntelliJ IDEA
 *
 * @author: Lu Yujie
 * on2018/11/2
 * 18:20
 */
@ConfigurationProperties(prefix = "leyou.jwt")
public class JwtProperties {

    private String secret; //密钥

    private String pubKeyPath; //公钥

    private String priKeyPath; //私钥

    private int expire; //token过期时间

    private PublicKey publicKey; //公钥

    private PrivateKey privateKey; //私钥

    private String cookieName; //cookie的名字

    private Integer cookieMaxAge; //cookie的生存时间

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProperties.class);

    /**
     * @PostContruct:在构造方法执行之后执行该方法
     */
    @PostConstruct
    public void init(){
        try {
            File pubKey = new File(pubKeyPath);
            File prikey = new File(priKeyPath);
            if (!pubKey.exists()||!prikey.exists()){
                //生成公钥和私钥
                RsaUtils.generateKey(pubKeyPath,priKeyPath,secret);
            }
            //获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);

        }catch (Exception e){
            LOGGER.error("初始化公钥和私钥失败",e);
            throw new RuntimeException();
        }
    }


    public JwtProperties() {
    }

    public JwtProperties(String secret, String pubKeyPath, String priKeyPath, int expire, PublicKey publicKey, PrivateKey privateKey, String cookieName, Integer cookieMaxAge) {
        this.secret = secret;
        this.pubKeyPath = pubKeyPath;
        this.priKeyPath = priKeyPath;
        this.expire = expire;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.cookieName = cookieName;
        this.cookieMaxAge = cookieMaxAge;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPubKeyPath() {
        return pubKeyPath;
    }

    public void setPubKeyPath(String pubKeyPath) {
        this.pubKeyPath = pubKeyPath;
    }

    public String getPriKeyPath() {
        return priKeyPath;
    }

    public void setPriKeyPath(String priKeyPath) {
        this.priKeyPath = priKeyPath;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public String getCookieName() {
        return cookieName;
    }

    public void setCookieName(String cookieName) {
        this.cookieName = cookieName;
    }

    public Integer getCookieMaxAge() {
        return cookieMaxAge;
    }

    public void setCookieMaxAge(Integer cookieMaxAge) {
        this.cookieMaxAge = cookieMaxAge;
    }
}
