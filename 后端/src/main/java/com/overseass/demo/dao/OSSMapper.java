package com.overseass.demo.dao;

import com.overseass.demo.pojo.Index;
import com.overseass.demo.pojo.OSS;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OSSMapper {

    @Select("select * from t_oss where str_type = #{typeName}")
    OSS selectByOssType(String typeName);

    @Select("select * from t_oss where str_name = #{strName}")
    List<OSS> selectByOssName(String strName);

    @Select("select * from t_oss where str_type = #{typeName}")
    List<OSS> getAllByType(String typeName);

    @Select("select * from t_oss where str_name = #{name} and str_attribute = #{attributeName}")
    OSS selectByNameAttributeName(String name, String attributeName);

    @Delete("delete from t_oss where str_name = #{strName}")
    int deleteByName(String strName);

    int deleteByPrimaryKey(Integer intOssId);

    int insert(OSS record);

    int insertSelective(OSS record);

    OSS selectByPrimaryKey(Integer intOssId);

    int updateByPrimaryKeySelective(OSS record);

    int updateByPrimaryKey(OSS record);
}