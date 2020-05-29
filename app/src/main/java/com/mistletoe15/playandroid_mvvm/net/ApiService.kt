package com.mistletoe15.playandroid_mvvm.net

import com.mistletoe15.playandroid_mvvm.bean.HomeArticleBean
import com.mistletoe15.playandroid_mvvm.bean.HomeBannerBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 请求数据Api
 * Created by Mistletoe on 2020/5/29
 **/
interface ApiService {
    @GET(BaseURL.BANNER_TAIL_URL)
    suspend fun getHomeBannerList():BaseResp<List<HomeBannerBean>>
    @GET("article/list/{pageIndex}/json")
    suspend fun getArticleListById(@Path("pageIndex") pageIndex:Int):BaseResp<List<HomeArticleBean>>
}