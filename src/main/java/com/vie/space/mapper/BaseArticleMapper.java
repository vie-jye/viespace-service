package com.vie.space.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vie.space.entity.BaseArticle;

public interface BaseArticleMapper extends BaseMapper<BaseArticle> {

    @Select("SELECT * FROM base_article WHERE id = #{id} and is_del = 0 limit 1")
    BaseArticle selectById(Long id);

    @Select("SELECT * FROM base_article WHERE title LIKE '%${title}%' AND is_del = 0")
    List<BaseArticle> selectByTitle(String title);

    @Select("SELECT * FROM base_article ORDER BY create_time DESC LIMIT #{limit}")
    List<BaseArticle> selectLatestArticles(int limit);

    @Insert("INSERT INTO base_article (create_time, title, description, content, article_type, tags, category_id, category, image, status, sort_order) VALUES (#{createTime}, #{title}, #{description}, #{content}, #{articleType}, #{tags}, #{categoryId}, #{category}, #{image}, #{status}, #{sortOrder})")
    int insertArticle(BaseArticle article);

    @Update("UPDATE base_article SET create_time = #{createTime}, title = #{title}, description = #{description}, content = #{content}, article_type = #{articleType}, tags = #{tags}, category_id = #{categoryId}, category = #{category}, image = #{image}, status = #{status}, sort_order = #{sortOrder}, update_time = #{updateTime} WHERE id = #{id}")
    int updateArticle(BaseArticle article);

    @Update("UPDATE base_article SET status = #{status} WHERE id = #{id}")
    int updateArticleStatus(Long id, Integer status);

    @Update("UPDATE base_article SET views = views + 1 WHERE id = #{id}")
    int increaseViews(Long id);
        
    @Update("UPDATE base_article SET is_del = 1 WHERE id = #{id}")
    int deleteArticleSoftly(Long id);

    @Delete("DELETE FROM base_article WHERE id = #{id}")
    int deleteArticleHardly(Long id);
}