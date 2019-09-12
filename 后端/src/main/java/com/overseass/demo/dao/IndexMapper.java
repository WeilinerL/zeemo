package com.overseass.demo.dao;

import com.overseass.demo.pojo.Index;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IndexMapper {

    @Select("select * from t_index where str_type = #{typeName}")
    Index selectByIndexType(String typeName);

    @Select("select * from t_index where str_name = #{name}")
    Index selectByIndexName(String name);

    @Select("select * from t_index where str_type = #{typeName}")
    List<Index> getAllBytype(String typeName);

    @Delete("delete from t_index where str_content = #{strContent}")
    int deleteByContent(String strContent);

    int deleteByPrimaryKey(Integer intIndexId);

    int insert(Index record);

    int insertSelective(Index record);

    Index selectByPrimaryKey(Integer intIndexId);

    int updateByPrimaryKeySelective(Index record);

    int updateByPrimaryKey(Index record);
}