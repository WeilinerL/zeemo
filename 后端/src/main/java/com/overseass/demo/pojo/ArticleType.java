package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class ArticleType {
    private Integer intTypeId;

    private String strType;

    public Integer getIntTypeId() {
        return intTypeId;
    }

    public void setIntTypeId(Integer intTypeId) {
        this.intTypeId = intTypeId;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType == null ? null : strType.trim();
    }
}