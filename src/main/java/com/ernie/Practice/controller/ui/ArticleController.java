package com.ernie.Practice.controller.ui;

import com.ernie.Practice.controller.form.ArticleFormData;
import com.ernie.Practice.dto.model.article.ArticleDto;
import com.ernie.Practice.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ModelAndView retrieveAll() {
        ModelAndView modelAndView = new ModelAndView("article_list");
        modelAndView.addObject("articles", articleService.findAllArticles());
        return modelAndView;
    }

    @GetMapping(path = "/{id}")
    public ModelAndView retrieveOne(@PathVariable String id) {
        int articleId = Integer.parseInt(id);
        ArticleDto articleDto = articleService.findArticleById(articleId);
        ModelAndView modelAndView = new ModelAndView("article_detail");
        modelAndView.addObject("article", articleDto);
        return modelAndView;
    }

    @GetMapping(path = "/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("article_create");
        modelAndView.addObject("articleFormData", new ArticleFormData());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView store(@ModelAttribute ArticleFormData articleFormData) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle(articleFormData.getTitle());
        articleDto.setContent(articleFormData.getContent());
        articleDto = articleService.createArticle(articleDto);

        ModelAndView modelAndView = new ModelAndView("article_detail");
        modelAndView.addObject("article", articleDto);
        return modelAndView;
    }

    @GetMapping(path = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id) {
        int articleId = Integer.parseInt(id);
        ArticleDto articleDto = articleService.findArticleById(articleId);

        ArticleFormData articleFormData = new ArticleFormData();
        articleFormData.setTitle(articleDto.getTitle());
        articleFormData.setContent(articleDto.getContent());

        ModelAndView modelAndView = new ModelAndView("article_edit");
        modelAndView.addObject("articleFormData", articleFormData);
        modelAndView.addObject("articleId", articleId);
        return modelAndView;
    }

    @PostMapping(path = "/{id}")
    public ModelAndView update(@PathVariable String id, @ModelAttribute ArticleFormData articleFormData) {
        int articleId = Integer.parseInt(id);

        ArticleDto articleDto = articleService.findArticleById(articleId);
        articleDto.setId(articleId);
        articleDto.setTitle(articleFormData.getTitle());
        articleDto.setContent(articleFormData.getContent());

        articleDto = articleService.updateArticle(articleDto);

        ModelAndView modelAndView = new ModelAndView("article_detail");
        modelAndView.addObject("article", articleDto);
        return modelAndView;
    }

}
