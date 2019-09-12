package com.overseass.demo.controller;

import com.overseass.demo.Service.OSIService;
import com.overseass.demo.pojo.OSI;
import com.overseass.demo.pojo.OSInfo;
import com.overseass.demo.pojo.SuccessList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/oversea_study_info")
public class OSIContoller {

    @Autowired
    private OSIService osiService;

    private final static Log log = LogFactory.getLog(SuccessListController.class);

    @GetMapping(value = "/articles")
    public List<OSInfo> getTitle() {
        if(log.isTraceEnabled()) {
            log.trace("获取所有文章");
        }
        return osiService.getAllOSInfo();
    }


    @GetMapping(value = "/article/{type}/{title}")
    public OSI getTitle(@PathVariable String  type, @PathVariable String title) {
        if(log.isTraceEnabled()) {
            log.trace("获取文章: " + type + title);
        }
        return osiService.getArticle(type,title);
    }

    @DeleteMapping(value = "delete_type/{content}")
    public Map<String,String> deleteSuccessList(@PathVariable String content) {
        Map<String,String> result = new HashMap<>();
        if(content != null) {
            if(osiService.deleteType(content) != 0) {
                result.put("result","success");
                return result;
            }else {
                result.put("result","fail");
                return result;
            }
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @PostMapping(value = "/add_type/{type}")
    public Map<String,String> addOss(@PathVariable String type) {
        Map<String,String> result = new HashMap<>();
        if(type != null) {
            if(log.isTraceEnabled()) {
                log.trace("增加分类信息: " + type);
            }
            if(osiService.addType(type) != 0) {
                result.put("result","success");
                return result;
            }else {
                result.put("result","fail");
                return result;
            }
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @DeleteMapping(value = "delete_article/{type}/{title}")
    public Map<String,String> deleteArticle(@PathVariable String  type, @PathVariable String title) {
        Map<String,String> result = new HashMap<>();
        if(type != null && title != null) {
            if(log.isTraceEnabled()) {
                log.trace("删除文章: " + type + title);
            }
            if(osiService.deleteArticle(type,title) != 0) {
                result.put("result","success");
                return result;
            }else {
                result.put("result","fail");
                return result;
            }
        }else {
            result.put("result","fail");
            return result;
        }
    }

    @PostMapping(value = "/add_article")
    public Map<String,String> addArticle(@RequestBody OSI osi) {
        Map<String,String> result = new HashMap<>();
        if(osi != null) {
            if(log.isTraceEnabled()) {
                log.trace("增加文章信息: " + osi.getStrTitle());
            }
            if(osiService.addArticle(osi) != 0) {
                result.put("result","success");
                return result;
            }else {
                result.put("result","fail");
                return result;
            }
        }else {
            result.put("result","fail");
            return result;
        }
    }
}
