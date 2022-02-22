package com.app.baassignmentapp.model.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ArticleResponse(@SerializedName("articles")
                              var articleList: List<ResultsItem>?)

data class ResultsItem(@SerializedName("author")
                      var author: String?,
                      @SerializedName("title")
                      var title: String?,
                      @SerializedName("description")
                      var description: String?,
                      @SerializedName("url")
                      var url: String?,
                      @SerializedName("urlToImage")
                      var urlToImage: String?,
                      @SerializedName("publishedAt")
                      var publishedAt: String?)

