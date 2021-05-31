package com.ernie.Practice.dto.mapper;

import com.ernie.Practice.dto.model.article.ArticleDto;
import com.ernie.Practice.entity.Article;

public class ArticleMapper {
    public static ArticleDto toArticleDto(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setTitle(article.getTitle());
        articleDto.setContent(article.getContent());
        return articleDto;
    }
}
