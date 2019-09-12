package com.overseass.demo.controller;

import com.overseass.demo.Service.LoginService;
import com.overseass.demo.pojo.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private final static Log log = LogFactory.getLog(LoginController.class);

    @PostMapping(value = "/login")
    public Map<String,String> login(@RequestBody User user) {
        Map<String,String> resultMap = new HashMap<>();
        if(log.isTraceEnabled()){
            log.trace("用户名为: " + user.getStrUserName() + "尝试登录");
        }
        if(loginService.login(user)) {
            resultMap.put("status","success");
            resultMap.put("userName",user.getStrUserName());
            return resultMap;
        }else {
            resultMap.put("status","fail");
            return resultMap;
        }
    }
}
