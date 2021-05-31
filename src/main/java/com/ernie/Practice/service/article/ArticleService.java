package com.ernie.Practice.service.article;

import com.ernie.Practice.dto.model.article.ArticleDto;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> findAllArticles();

    ArticleDto findArticleById(int articleId);

    ArticleDto createArticle(ArticleDto articleDto);

    ArticleDto updateArticle(ArticleDto articleDto);

    Boolean deleteArticle(int articleId);
}
