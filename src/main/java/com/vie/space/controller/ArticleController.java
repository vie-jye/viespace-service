package com.vie.space.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vie.space.core.pagehelper.PageData;
import com.vie.space.entity.BaseArticle;
import com.vie.space.service.BaseArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private BaseArticleService baseArticleService;

    /**
     * 获取文章详情
     */
    @GetMapping("detail/{id}")
    public ResponseEntity<BaseArticle> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(baseArticleService.selectById(id));
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
            
        PageData<BaseArticle> list = baseArticleService.findArticlesWithPage(startYear, endYear, categoryId, page, pageSize);
        return ResponseEntity.ok(list);
    }

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Integer> addArticle(@RequestBody BaseArticle article) {
        int value = baseArticleService.insertArticle(article);
        return ResponseEntity.ok(value);
    }

    /**
     * 更新文章
     * @param id
     * @param article
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Integer> updateArticle(@PathVariable Long id, @RequestBody BaseArticle article) {
        article.setId(id);
        int value = baseArticleService.updateArticle(article);
        return ResponseEntity.ok(value);
    }

    /**
     * 更新文章状态
     * @param id
     * @param article
     */
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<Integer> updateArticle(@PathVariable Long id) {
        BaseArticle baseArticle = baseArticleService.selectById(id);
        Integer targetStatus = baseArticle.getStatus() == 1 ? 0 : 1;
        int value = baseArticleService.updateArticleStatus(id, targetStatus);
        return ResponseEntity.ok(value);
    }

    /**
     * 删除文章
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> deleteArticle(@PathVariable Long id) {
        int value = baseArticleService.deleteArticleSoftly(id);
        return ResponseEntity.ok(value);
    }
}