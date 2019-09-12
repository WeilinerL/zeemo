package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class OSSInfo {

    private String ossTitle;

    private String content;

    private String bacColor;

    public String getOssTitle() {
        return ossTitle;
    }

    public void setOssTitle(String ossTitle) {
        this.ossTitle = ossTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBacColor() {
        return bacColor;
    }

    public void setBacColor(String bacColor) {
        this.bacColor = bacColor;
    }
}
