package com.mistletoe15.playandroid_mvvm.data.paging.article

import androidx.paging.PageKeyedDataSource
import com.mistletoe15.playandroid_mvvm.data.bean.HomeArticleBean
import com.mistletoe15.playandroid_mvvm.data.net.ApiService
import com.mistletoe15.playandroid_mvvm.data.net.RetrofitFactory
import com.mistletoe15.playandroid_mvvm.data.net.handled
import kotlinx.coroutines.*
import kotlin.properties.Delegates

/**
 * Created by Mistletoe on 2020/6/3
 **/
class ArticleDataSource: PageKeyedDataSource<Int, HomeArticleBean>() {
    private var job by Delegates.notNull<Job>()
    //初次加载
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, HomeArticleBean>) {
        job = GlobalScope.launch (Dispatchers.IO) {
            val articleList =RetrofitFactory.instance.getService(ApiService::class.java)
                .getArticleListById(0).handled().datas
            launch(Dispatchers.Main) {
                callback.onResult(articleList, 0, 1)
                job.cancel()
            }
        }
    }
    //后面一页加载
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, HomeArticleBean>) {
        job = GlobalScope.launch (Dispatchers.IO) {
            val articleList = RetrofitFactory.instance.getService(ApiService::class.java)
                .getArticleListById(params.key).handled().datas
            launch(Dispatchers.Main) {
                callback.onResult(articleList, params.key+1)
                job.cancel()
            }
        }
    }
   //前一页加载
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, HomeArticleBean>) {
       if(params.key > 0){
           job = GlobalScope.launch (Dispatchers.IO) {
               val articleList = RetrofitFactory.instance.getService(ApiService::class.java)
                   .getArticleListById(params.key).handled().datas
               launch(Dispatchers.Main) {
                   callback.onResult(articleList, params.key-1)
                   job.cancel()
               }
           }
       }
    }
}