package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class OSI {
    private Integer intOsiId;

    private String strType;

    private String strTitle;

    private String strAuthor;

    private String strTime;

    private String strContent;

    public Integer getIntOsiId() {
        return intOsiId;
    }

    public void setIntOsiId(Integer intOsiId) {
        this.intOsiId = intOsiId;
    }

    public String getStrType() {
        return strType;
    }

    public void setStrType(String strType) {
        this.strType = strType == null ? null : strType.trim();
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle == null ? null : strTitle.trim();
    }

    public String getStrAuthor() {
        return strAuthor;
    }

    public void setStrAuthor(String strAuthor) {
        this.strAuthor = strAuthor == null ? null : strAuthor.trim();
    }

    public String getStrTime() {
        return strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime == null ? null : strTime.trim();
    }

    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent == null ? null : strContent.trim();
    }
}