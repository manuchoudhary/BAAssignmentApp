package com.app.baassignmentapp.datasource.repo

import android.annotation.SuppressLint
import android.util.Log
import com.app.baassignmentapp.datasource.mapper.ArticleMapper
import com.app.baassignmentapp.model.Article
import com.app.baassignmentapp.model.local.storage.StorageIntf
import com.app.baassignmentapp.model.remote.BAApiService
import com.app.baassignmentapp.utils.Constants.KEY_END
import com.google.gson.JsonObject
import retrofit2.Response

class ArticleRepository constructor(private var apiService: BAApiService,
                                    private var storageIntf: StorageIntf) {

    @SuppressLint("LogNotTimber")
    suspend fun getArticle() : ArrayList<Article> {
        var articleList = ArticleMapper.responseToArticle(storageIntf.getArticle(KEY_END))
        val resObject: Response<JsonObject>?
        if (articleList.size == 0) {
            try {
                resObject = apiService.callArticles().execute()
                storageIntf.setArticle(KEY_END, resObject.body().toString())
                articleList = ArticleMapper.objectToArticleList(resObject.body().toString())
            } catch (exception: Exception) {
                Log.e(ArticleRepository::class.java.name, exception.message.toString())
            }
        }
        return articleList
    }
}