package com.app.baassignmentapp.view.detail

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.baassignmentapp.R
import com.app.baassignmentapp.databinding.ActivityDetailBinding
import com.app.baassignmentapp.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var activityDetailBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val article: Article = intent.getSerializableExtra("article") as Article
        activityDetailBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_detail
        )
        activityDetailBinding.webView.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                webViewClient = webViewClient
            }
            loadUrl(article.url)
        }
    }
}