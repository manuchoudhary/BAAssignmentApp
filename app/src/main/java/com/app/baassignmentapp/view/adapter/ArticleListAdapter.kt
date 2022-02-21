package com.app.baassignmentapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.baassignmentapp.databinding.ArticleItemBinding
import com.app.baassignmentapp.model.Article
import com.bumptech.glide.Glide

class ArticleListAdapter(
    private val context: Context?,
    private val articleList: ArrayList<Article>) : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleListAdapter.ArticleViewHolder {
        val binding =
            ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleListAdapter.ArticleViewHolder, position: Int) {
        val item = articleList[position]
        holder.apply {
            setIsRecyclable(false)
            title.text = item.title
            description.text = item.description
            author.text = item.author
            if (context != null) {
                Glide.with(context)
                    .load(item.urlToImage)
                    .centerCrop()
                    .into(image)
            }
            itemView.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class ArticleViewHolder(private val binding: ArticleItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val image = binding.itemArticleImage
        val title = binding.itemArticleTitle
        val description = binding.itemPostDescription
        val author = binding.itemPostAuthor
    }
}