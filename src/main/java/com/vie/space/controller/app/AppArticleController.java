package com.vie.space.controller.app;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vie.space.core.enume.CategoryEnum;
import com.vie.space.core.pagehelper.PageData;
import com.vie.space.entity.BaseArticle;
import com.vie.space.service.BaseArticleService;

@RestController
@RequestMapping("/app/article")
public class AppArticleController {

    @Autowired
    private BaseArticleService baseArticleService;

    /**
     * 获取文章详情
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<BaseArticle> getArticleById(@PathVariable Long id) {
        BaseArticle article = baseArticleService.selectById(id);
        if (article != null) {
            baseArticleService.increaseViews(id);
        }
        return ResponseEntity.ok(article);
    }

    /**
     * 获取首页文章列表
     * @return
     */
    @GetMapping("/home")
    public ResponseEntity<Map<String, List<BaseArticle>>> homeArticles() {
        List<BaseArticle> featured = baseArticleService.findArticlesOrderByDesc(null, null, null);
        List<BaseArticle> programming = baseArticleService.findArticlesByYearRangeAndCategoryId(null, null, CategoryEnum.PROGRAMMING_TECH.getCategoryId());
        List<BaseArticle> devLogs = baseArticleService.findArticlesByYearRangeAndCategoryId(null, null, CategoryEnum.DEVELOPMENT_LOGS.getCategoryId());
        List<BaseArticle> travelogue = baseArticleService.findArticlesByYearRangeAndCategoryId(null, null, CategoryEnum.TRAVELOGUE.getCategoryId());
        List<BaseArticle> poetry = baseArticleService.findArticlesByYearRangeAndCategoryId(null, null, CategoryEnum.POETRY.getCategoryId());
        List<BaseArticle> photography = baseArticleService.findArticlesByYearRangeAndCategoryId(null, null, CategoryEnum.PHOTOGRAPHY.getCategoryId());
        return ResponseEntity.ok(Map.of("featured", featured, "programming", programming, "devLogs", devLogs, "travelogue", travelogue, "poetry", poetry, "photography", photography));
    }

    /**
     * 获取文章列表
     * @param startYear
     * @param endYear
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseEntity<PageData<BaseArticle>> findArticles(
            @RequestParam(required = false) Long startYear,
            @RequestParam(required = false) Long endYear,
            @RequestParam(required = false) Long categoryId,
            @RequestParam int page,
            @RequestParam int pageSize) {

        PageData<BaseArticle> list = baseArticleService.findArticlesWithPage(startYear, endYear, categoryId, page,
                pageSize);
        return ResponseEntity.ok(list);
    }
}
