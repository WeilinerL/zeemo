package com.overseass.demo.dao;

import com.overseass.demo.pojo.SummerCamp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SummerCampMapper {

    @Select("select * from t_summer_camp where str_title = #{title}")
    SummerCamp selectByTitle(String title);

    @Select("select * from t_summer_camp where str_title != #{title}")
    List<SummerCamp> selectAllExcept(String title);
    
    @Delete("delete from t_summer_camp where str_title = #{title}")
    int deleteByTitle(String title);

    int deleteByPrimaryKey(Integer intSmId);

    int insert(SummerCamp record);

    int insertSelective(SummerCamp record);

    SummerCamp selectByPrimaryKey(Integer intSmId);

    int updateByPrimaryKeySelective(SummerCamp record);

    int updateByPrimaryKey(SummerCamp record);
}