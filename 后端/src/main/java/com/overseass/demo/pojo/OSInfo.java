package com.overseass.demo.pojo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OSInfo {

    private String type;

    private List<String> articleList = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<String> getArticleList() {
        return articleList;
    }

    public void addArticle(String article) {
        this.articleList.add(article);
    }
}
