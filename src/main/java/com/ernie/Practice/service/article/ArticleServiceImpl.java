package com.ernie.Practice.service.article;

import com.ernie.Practice.dto.mapper.ArticleMapper;
import com.ernie.Practice.dto.model.article.ArticleDto;
import com.ernie.Practice.entity.Article;
import com.ernie.Practice.exception.EntityNotFoundException;
import com.ernie.Practice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleDto> findAllArticles() {
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article : articleRepository.findAll()) {
            articleDtos.add(ArticleMapper.toArticleDto(article));
        }
        return articleDtos;
    }

    @Override
    public ArticleDto findArticleById(int articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isPresent()) {
            return ArticleMapper.toArticleDto(optionalArticle.get());
        }
        throw new EntityNotFoundException(Article.class, "id", String.valueOf(articleId));
    }

    @Override
    public ArticleDto createArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setTitle(articleDto.getTitle());
        article.setContent(articleDto.getContent());
        articleRepository.save(article);
        return ArticleMapper.toArticleDto(article);
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        Optional<Article> optionalArticle = articleRepository.findById(articleDto.getId());
        if (optionalArticle.isPresent()) {
            Article article = optionalArticle.get();
            article.setTitle(articleDto.getTitle());
            article.setContent(articleDto.getContent());
            articleRepository.save(article);
            return ArticleMapper.toArticleDto(article);
        }
        throw new EntityNotFoundException(Article.class, "id", String.valueOf(articleDto.getId()));
    }

    @Override
    public Boolean deleteArticle(int articleId) {
        if (articleRepository.existsById(articleId)) {
            articleRepository.deleteById(articleId);
        }
        return !articleRepository.existsById(articleId);
    }
}
