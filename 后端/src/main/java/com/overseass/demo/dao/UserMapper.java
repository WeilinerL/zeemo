package com.overseass.demo.dao;

import com.overseass.demo.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    @Select("select * from t_user_a where str_user_name = #{userName}")
    User selectByUserName(String userName);

    int deleteByPrimaryKey(Integer intUserId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer intUserId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}