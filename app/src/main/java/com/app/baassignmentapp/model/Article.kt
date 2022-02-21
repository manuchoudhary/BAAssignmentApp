package com.app.baassignmentapp.model

import java.io.Serializable

data class Article(
    var author: String,
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String
) : Serializable