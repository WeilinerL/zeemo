package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class SuccessList {
    private Integer tListId;

    private String strPicture;

    private String strContent;

    public Integer gettListId() {
        return tListId;
    }

    public void settListId(Integer tListId) {
        this.tListId = tListId;
    }

    public String getStrPicture() {
        return strPicture;
    }

    public void setStrPicture(String strPicture) {
        this.strPicture = strPicture == null ? null : strPicture.trim();
    }

    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent == null ? null : strContent.trim();
    }
}