package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class OSS {
    private Integer intOssId;

    private String strType;

    private String strName;

    private String strAttribute;

    private String strContent;

    public Integer getIntOssId() {
        return intOssId;
    }

    public void setIntOssId(Integer intOssId) {
        this.intOssId = intOssId;
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

    public String getStrAttribute() {
        return strAttribute;
    }

    public void setStrAttribute(String strAttribute) {
        this.strAttribute = strAttribute == null ? null : strAttribute.trim();
    }

    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent == null ? null : strContent.trim();
    }
}