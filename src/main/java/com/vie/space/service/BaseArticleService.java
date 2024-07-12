package com.vie.space.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vie.space.core.pagehelper.PageData;
import com.vie.space.entity.BaseArticle;

public interface BaseArticleService extends IService<BaseArticle> {

    BaseArticle selectById(Long id);

    List<BaseArticle> selectByTitle(String title);

    PageData<BaseArticle> findArticlesWithPage(Long startYear, Long endYear, Long categoryId, int page, int pageSize);

    List<BaseArticle> findArticlesByYearRangeAndCategoryId(Long startYear, Long endYear, Long categoryId);

    List<BaseArticle> findArticlesOrderByDesc(Long startYear, Long endYear, Long categoryId);

    int insertArticle(BaseArticle article);

    int updateArticle(BaseArticle article);

    int updateArticleStatus(Long id, Integer status);

    int increaseViews(Long id);

    int deleteArticleSoftly(Long id);

    int deleteArticleHardly(Long id);
}