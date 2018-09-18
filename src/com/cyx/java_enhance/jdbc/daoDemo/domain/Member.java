package com.cyx.java_enhance.jdbc.daoDemo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Member {

    private Long id;
    private String account;         // 账号
    private String password;        // 密码
    private Integer balance;        // 余额

}
