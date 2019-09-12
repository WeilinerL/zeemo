package com.overseass.demo.dao;

import com.overseass.demo.pojo.Consultant;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConsultantMapper {

    @Select("select * from t_consultant")
    List<Consultant> getAllConsultants();

    @Select("select * from t_consultant where str_name = #{name}")
    List<Consultant> selectByName(String name);

    @Delete("delete from t_consultant where str_name = #{name}")
    int deleteByName(String name);

    int deleteByPrimaryKey(Integer intConsultantId);

    int insert(Consultant record);

    int insertSelective(Consultant record);

    Consultant selectByPrimaryKey(Integer intConsultantId);

    int updateByPrimaryKeySelective(Consultant record);

    int updateByPrimaryKey(Consultant record);
}