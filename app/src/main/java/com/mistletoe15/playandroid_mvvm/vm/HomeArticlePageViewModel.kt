package com.mistletoe15.playandroid_mvvm.vm

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mistletoe15.playandroid_mvvm.data.paging.article.ArticlePageKeyDataSourceFactory

/**
 * Created by Mistletoe on 2020/6/3
 **/
class HomeArticlePageViewModel:ViewModel() {
    val livePagedListBuilder = LivePagedListBuilder(
        ArticlePageKeyDataSourceFactory(), PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(20)
        .build()).build()
}