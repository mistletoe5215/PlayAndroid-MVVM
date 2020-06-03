package com.mistletoe15.playandroid_mvvm.data.paging.article

import androidx.paging.DataSource
import com.mistletoe15.playandroid_mvvm.data.bean.HomeArticleBean

/**
 * Created by Mistletoe on 2020/6/3
 **/
class ArticlePageKeyDataSourceFactory: DataSource.Factory<Int, HomeArticleBean>() {
    override fun create(): DataSource<Int, HomeArticleBean> {
      return ArticleDataSource()
    }
}