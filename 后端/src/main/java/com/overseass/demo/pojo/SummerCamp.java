package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class SummerCamp {
    private Integer intSmId;

    private String strTitle;

    private String strContent;

    private String strImg;

    public Integer getIntSmId() {
        return intSmId;
    }

    public void setIntSmId(Integer intSmId) {
        this.intSmId = intSmId;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle == null ? null : strTitle.trim();
    }

    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent == null ? null : strContent.trim();
    }

    public String getStrImg() {
        return strImg;
    }

    public void setStrImg(String strImg) {
        this.strImg = strImg == null ? null : strImg.trim();
    }
}