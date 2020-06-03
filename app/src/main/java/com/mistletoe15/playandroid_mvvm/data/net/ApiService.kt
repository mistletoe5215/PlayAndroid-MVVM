package com.mistletoe15.playandroid_mvvm.data.net
import com.mistletoe15.playandroid_mvvm.data.bean.HomeBannerBean
import com.mistletoe15.playandroid_mvvm.data.bean.HomePageArticleBean
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
    suspend fun getArticleListById(@Path("pageIndex") pageIndex:Int):BaseResp<HomePageArticleBean>
}