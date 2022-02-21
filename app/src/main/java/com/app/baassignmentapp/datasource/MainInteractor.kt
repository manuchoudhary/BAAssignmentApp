package com.app.baassignmentapp.datasource

import com.app.baassignmentapp.boot.BootSequence
import com.app.baassignmentapp.datasource.repo.ArticleRepository
import com.app.baassignmentapp.model.Article

class MainInteractor(private var articleRepository: ArticleRepository) : BootSequence {
    override suspend fun callArticle() : ArrayList<Article> {
        return articleRepository.getArticle()
    }
}