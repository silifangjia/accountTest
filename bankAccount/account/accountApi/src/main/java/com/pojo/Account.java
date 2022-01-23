package com.pojo;


import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.util.Date;


public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;// 用户id

    private String gender;// 性别

    @Length(min = 4,max = 30, message = "用户名只能在4-30位之间")
    private String username;// 用户名

    @Pattern(regexp = "^1[35678]\\d{9}$",message = "手机号格式不正确")
    private String phone;// 电话

    private double balance = 0;//账户余额

    private static double annuallnterestRate = 0;//账户年利率

    private Date created;// 创建时间

    //用于存储账户所有的交易信息

    private Transaction[] transactions;

    public Account() {
    }

    public Account(String username, String phone) {
        this.username = username;
        this.phone = phone;
        this.created = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = new Date();
    }

    public double getBalance() {
        return balance;

    }

    public void setBalance(double balance) {
        this.balance = balance;

    }

    public static double getAnnuallnterestRate() {
        return annuallnterestRate;

    }

    public static void setAnnuallnterestRate(double annuallnterestRate) {
        Account.annuallnterestRate = annuallnterestRate;

    }
}
