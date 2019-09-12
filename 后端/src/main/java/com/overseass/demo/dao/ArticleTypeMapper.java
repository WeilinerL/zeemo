package com.overseass.demo.dao;

import com.overseass.demo.pojo.ArticleType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface ArticleTypeMapper {

    @Select("select * from t_article_type where str_type = #{typeName}")
    ArticleType selectByTypeName(String typeName);

    @Delete("delete from t_article_type where str_type = #{typeName}")
    int deleteByType(String typeName);

    int deleteByPrimaryKey(Integer intTypeId);

    int insert(ArticleType record);

    int insertSelective(ArticleType record);

    ArticleType selectByPrimaryKey(Integer intTypeId);

    int updateByPrimaryKeySelective(ArticleType record);

    int updateByPrimaryKey(ArticleType record);
}