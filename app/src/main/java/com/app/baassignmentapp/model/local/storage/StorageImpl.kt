package com.app.baassignmentapp.model.local.storage

import com.app.baassignmentapp.model.local.database.AppDB
import com.app.baassignmentapp.model.local.entity.ApiResponseEntity
import com.app.baassignmentapp.model.remote.response.ArticleResponse
import com.google.gson.Gson

class StorageImpl(private var appDb: AppDB) : StorageIntf {
    override suspend fun getArticle(section: String): ArticleResponse {
        val responseEntity = appDb.articleDao().getValueByKey(section)
        return if (responseEntity != null && !responseEntity.apiValue.isNullOrBlank()
            && responseEntity.apiValue != "null") {
            Gson().fromJson(responseEntity.apiValue, ArticleResponse::class.java)
        } else {
            ArticleResponse(arrayListOf())
        }
    }

    override suspend fun setArticle(section: String, response: String) {
        val responseEntity = ApiResponseEntity(section, response, System.currentTimeMillis())
        appDb.articleDao().upsert(responseEntity)
    }

}