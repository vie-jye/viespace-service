package com.vie.space.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.vie.space.core.enume.CategoryEnum;
import com.vie.space.core.pagehelper.PageData;
import com.vie.space.entity.BaseArticle;
import com.vie.space.mapper.BaseArticleMapper;
import com.vie.space.service.BaseArticleService;

@Service("baseArticleService")
public class BaseArticleServiceImpl extends ServiceImpl<BaseArticleMapper, BaseArticle> implements BaseArticleService {

    @Autowired
    private BaseArticleMapper baseArticleMapper;

    @Override
    public BaseArticle selectById(Long id) {
        return baseArticleMapper.selectById(id);
    }

    @Override
    public List<BaseArticle> selectByTitle(String title) {
        return baseArticleMapper.selectByTitle(title);
    }

    @Override
    public PageData<BaseArticle> findArticlesWithPage(Long startYear, Long endYear, Long categoryId, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<BaseArticle> list = this.findArticlesByYearRangeAndCategoryId(startYear, endYear, categoryId);
        return PageData.pack(list);
    }

    @Override
    public List<BaseArticle> findArticlesByYearRangeAndCategoryId(Long startYear, Long endYear, Long categoryId) {
        List<BaseArticle> list = baseArticleMapper.selectList(new QueryWrapper<BaseArticle>().lambda()
        .select(BaseArticle::getId, BaseArticle::getTitle, BaseArticle::getDescription, BaseArticle::getArticleType, BaseArticle::getCategoryId, BaseArticle::getCategory, BaseArticle::getCreateTime, BaseArticle::getUpdateTime, BaseArticle::getViews, BaseArticle::getImage, BaseArticle::getTags, BaseArticle::getStatus)
        .eq(categoryId != null && categoryId > 0, BaseArticle::getCategoryId, categoryId)
        .eq(BaseArticle::getIsDel, 0)
        .between(startYear!= null && startYear > 0, BaseArticle::getCreateTime, startYear, endYear)
        .orderByDesc(BaseArticle::getCreateTime));
        return list != null? list : new ArrayList<>();
    }

    @Override
    public List<BaseArticle> findArticlesOrderByDesc(Long startYear, Long endYear, Long categoryId) {
        List<BaseArticle> list = baseArticleMapper.selectList(new QueryWrapper<BaseArticle>().lambda()
        .select(BaseArticle::getId, BaseArticle::getTitle, BaseArticle::getDescription, BaseArticle::getArticleType, BaseArticle::getCategoryId, BaseArticle::getCategory, BaseArticle::getCreateTime, BaseArticle::getViews, BaseArticle::getImage, BaseArticle::getTags)
        .eq(categoryId != null && categoryId > 0, BaseArticle::getCategoryId, categoryId)
        .eq(BaseArticle::getIsDel, 0)
        .between(startYear!= null && startYear > 0, BaseArticle::getCreateTime, startYear, endYear)
        .orderByDesc(BaseArticle::getSortOrder)
        .orderByDesc(BaseArticle::getCreateTime)
        .last("LIMIT 6"));
        return list != null? list : new ArrayList<>();
    }

    @Override
    public int insertArticle(BaseArticle article) {
        Long cateId = article.getCategoryId();
        CategoryEnum cate = CategoryEnum.getByCategoryId(cateId);
        article.setCategory(cate.getCategory());
        return baseArticleMapper.insertArticle(article);
    }

    @Override
    public int updateArticle(BaseArticle article) {
        article.setUpdateTime(System.currentTimeMillis()/1000);
        Long cateId = article.getCategoryId();
        CategoryEnum cate = CategoryEnum.getByCategoryId(cateId);
        article.setCategory(cate.getCategory());
        return baseArticleMapper.updateArticle(article);
    }

    @Override
    public int updateArticleStatus(Long id, Integer status) {
        return baseArticleMapper.updateArticleStatus(id, status);
    }

    @Override
    public int increaseViews(Long id) {
        return baseArticleMapper.increaseViews(id);
    }

    @Override
    public int deleteArticleSoftly(Long id) {
        return baseArticleMapper.deleteArticleSoftly(id);
    }

    @Override
    public int deleteArticleHardly(Long id) {
        return baseArticleMapper.deleteArticleHardly(id);
    }
}