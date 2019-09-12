package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class User {
    private Integer intUserId;

    private String strUserName;

    private String strPassword;

    public Integer getIntUserId() {
        return intUserId;
    }

    public void setIntUserId(Integer intUserId) {
        this.intUserId = intUserId;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName == null ? null : strUserName.trim();
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword == null ? null : strPassword.trim();
    }
}