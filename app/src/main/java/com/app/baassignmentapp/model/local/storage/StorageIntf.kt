package com.app.baassignmentapp.model.local.storage

import com.app.baassignmentapp.model.remote.response.ArticleResponse

interface StorageIntf {
    suspend fun getArticle(section: String) : ArticleResponse
    suspend fun setArticle(section: String, response: String)
}