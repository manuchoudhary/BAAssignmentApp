package com.app.baassignmentapp.datasource.mapper

import com.app.baassignmentapp.model.Article
import com.app.baassignmentapp.model.remote.response.ArticleResponse
import com.google.gson.Gson

object ArticleMapper {
    fun responseToArticle(response: ArticleResponse) : ArrayList<Article> {
        val articleList: ArrayList<Article> = arrayListOf()
        if (!response.articleList.isNullOrEmpty()) {
            for (item in response.articleList!!) {
                articleList.add(
                    Article(
                        item.author?: "Anonymous",
                        item.title?: "",
                        item.description?: "",
                        item.url?: "",
                        item.urlToImage?: "",
                        item.publishedAt?: ""
                    )
                )
            }
        }
        return articleList
    }

    fun objectToArticleList(response: String) : ArrayList<Article> {
        if (response.isNotEmpty() && response != "null") {
            val result = Gson().fromJson(response, ArticleResponse::class.java)
            return responseToArticle(result)
        }
        return arrayListOf()
    }
}