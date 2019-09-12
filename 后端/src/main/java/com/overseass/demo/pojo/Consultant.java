package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class Consultant {
    private Integer intConsultantId;

    private String strPicture;

    private String strName;

    private String strTitle;

    private String strIntroduction;

    public Integer getIntConsultantId() {
        return intConsultantId;
    }

    public void setIntConsultantId(Integer intConsultantId) {
        this.intConsultantId = intConsultantId;
    }

    public String getStrPicture() {
        return strPicture;
    }

    public void setStrPicture(String strPicture) {
        this.strPicture = strPicture == null ? null : strPicture.trim();
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName == null ? null : strName.trim();
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle == null ? null : strTitle.trim();
    }

    public String getStrIntroduction() {
        return strIntroduction;
    }

    public void setStrIntroduction(String strIntroduction) {
        this.strIntroduction = strIntroduction == null ? null : strIntroduction.trim();
    }
}