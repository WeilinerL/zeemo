package com.overseass.demo.Service;

import com.overseass.demo.dao.UserMapper;
import com.overseass.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public Boolean login(User user) {
        User u = userMapper.selectByUserName(user.getStrUserName());
        if(u != null && u.getStrPassword().equals(user.getStrPassword())) {
            return true;
        }
        return false;
    }
}
