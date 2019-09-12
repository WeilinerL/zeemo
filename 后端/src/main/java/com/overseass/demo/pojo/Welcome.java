package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class Welcome {

    private String backPic;

    private String proverbTitle;

    private String proverbContent;

    public String getBackPic() {
        return backPic;
    }

    public void setBackPic(String backPic) {
        this.backPic = backPic;
    }

    public String getProverbTitle() {
        return proverbTitle;
    }

    public void setProverbTitle(String proverbTitle) {
        this.proverbTitle = proverbTitle;
    }

    public String getProverbContent() {
        return proverbContent;
    }

    public void setProverbContent(String proverbContent) {
        this.proverbContent = proverbContent;
    }
}
