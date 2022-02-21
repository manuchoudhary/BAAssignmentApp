package com.app.baassignmentapp.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.baassignmentapp.R
import com.app.baassignmentapp.databinding.ActivityMainBinding
import com.app.baassignmentapp.view.adapter.ArticleListAdapter
import com.app.baassignmentapp.view.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val articleViewModel: ArticleViewModel by viewModels()
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        setObserver()
        articleViewModel.getArticle()
    }

    private fun setObserver() {
        val recyclerView = activityMainBinding.articleRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        articleViewModel.articleList.observe(this) { articleList ->
            val adapter = ArticleListAdapter(this, articleList)
            adapter.setOnItemClickListener { article ->
                val intent = Intent(this, DetailActivity::class.java)
                    .putExtra("article", article)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        }
    }
}