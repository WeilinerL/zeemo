package com.overseass.demo.dao;

import com.overseass.demo.pojo.OSI;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OSIMapper {

    @Select("select * from t_osi")
    List<OSI> selectAllOSInfo();

    @Select("select * from t_osi where str_type = #{type} and str_title = #{title}")
    OSI selectArticle(String type,String title);

    @Delete("delete from t_osi where str_type = #{type}")
    int deleteByType(String type);

    @Delete("delete from t_osi where str_type = #{type} and str_title = #{title}")
    int deleteByTitle(String type, String title);

    int deleteByPrimaryKey(Integer intOsiId);

    int insert(OSI record);

    int insertSelective(OSI record);

    OSI selectByPrimaryKey(Integer intOsiId);

    int updateByPrimaryKeySelective(OSI record);

    int updateByPrimaryKeyWithBLOBs(OSI record);

    int updateByPrimaryKey(OSI record);
}