package com.overseass.demo.dao;

import com.overseass.demo.pojo.SuccessList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SuccessListMapper {

    @Select("select * from t_success_list")
    List<SuccessList> getAllSuccessList();

    @Select("select * from t_success_list where str_content = #{content}")
    List<SuccessList> selectByContent(String content);

    @Delete("delete from t_success_list where str_content = #{content}")
    int deleteByContent(String content);

    int deleteByPrimaryKey(Integer tListId);

    int insert(SuccessList record);

    int insertSelective(SuccessList record);

    SuccessList selectByPrimaryKey(Integer tListId);

    int updateByPrimaryKeySelective(SuccessList record);

    int updateByPrimaryKey(SuccessList record);
}