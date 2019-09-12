package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class ServiceFlow {

    private String SFbackPic;

    private String SFTitle;

    private String SFContent;

    private String SFBackColor;

    public String getSFbackPic() {
        return SFbackPic;
    }

    public void setSFbackPic(String SFbackPic) {
        this.SFbackPic = SFbackPic;
    }

    public String getSFTitle() {
        return SFTitle;
    }

    public void setSFTitle(String SFTitle) {
        this.SFTitle = SFTitle;
    }

    public String getSFContent() {
        return SFContent;
    }

    public void setSFContent(String SFContent) {
        this.SFContent = SFContent;
    }

    public String getSFBackColor() {
        return SFBackColor;
    }

    public void setSFBackColor(String SFBackColor) {
        this.SFBackColor = SFBackColor;
    }
}
