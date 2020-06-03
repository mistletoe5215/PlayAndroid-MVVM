package com.mistletoe15.playandroid_mvvm.ui.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.data.bean.HomeArticleBean
import com.mistletoe15.playandroid_mvvm.ui.activity.WebViewPage

/**
 * Created by Mistletoe on 2020/6/3
 **/
class HomeArticleAdapter : PagedListAdapter<HomeArticleBean, HomeArticleAdapter.HomeArticleViewHolder>(DIFF_CALLBACK) {
    inner class HomeArticleViewHolder(itemLayout: RelativeLayout) : RecyclerView.ViewHolder(itemLayout){
        val titleText: TextView = itemLayout.findViewById(R.id.article_tv_title)
        val niceDateText:TextView = itemLayout.findViewById(R.id.article_tv_nice_date)
        val userText:TextView = itemLayout.findViewById(R.id.article_tv_user)
    }
    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<HomeArticleBean>() {
            // Article details may have changed if reloaded from the net,
            // but ID is fixed.
            override fun areItemsTheSame(oldArticle:  HomeArticleBean, newArticle:  HomeArticleBean) = oldArticle.id == newArticle.id
            override fun areContentsTheSame(oldArticle:  HomeArticleBean, newArticle:  HomeArticleBean) = oldArticle == newArticle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item_view, parent,false) as RelativeLayout
        return  HomeArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeArticleViewHolder, position: Int) {
        val article: HomeArticleBean? = getItem(position)
        holder.apply {
            titleText.text = article?.title
            niceDateText.text = article?.niceDate
            userText.text = if(article?.author !== "") article?.author else article.shareUser
            val intent = Intent(itemView.context,  WebViewPage::class.java).apply {
                putExtra("url",article?.link)
            }
            itemView.setOnClickListener {
                ContextCompat.startActivity(itemView.context, intent, Bundle())
            }
        }
    }
}
