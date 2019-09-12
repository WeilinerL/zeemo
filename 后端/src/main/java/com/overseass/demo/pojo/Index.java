package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class Index {
    private Integer intIndexId;

    private String strType;

    private String strName;

    private String strContent;

    public Integer getIntIndexId() {
        return intIndexId;
    }

    public void setIntIndexId(Integer intIndexId) {
        this.intIndexId = intIndexId;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType == null ? null : strType.trim();
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName == null ? null : strName.trim();
    }

    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent == null ? null : strContent.trim();
    }
}