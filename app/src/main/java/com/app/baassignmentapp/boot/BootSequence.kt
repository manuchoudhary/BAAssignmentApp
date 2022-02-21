package com.app.baassignmentapp.boot

import com.app.baassignmentapp.model.Article

const val BOOT_ARTS = "boot_arts"

interface BootSequence {
    suspend fun callArticle() : ArrayList<Article>
}