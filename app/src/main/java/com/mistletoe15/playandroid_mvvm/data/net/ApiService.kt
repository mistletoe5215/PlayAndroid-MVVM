package com.mistletoe15.playandroid_mvvm.data.net
import com.mistletoe15.playandroid_mvvm.data.bean.HomeBannerModel
import com.mistletoe15.playandroid_mvvm.data.bean.HomePageArticleModel
import com.mistletoe15.playandroid_mvvm.data.bean.HomeTopRankModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 请求数据Api
 * Created by Mistletoe on 2020/5/29
 **/
interface ApiService {
    @GET(BaseURL.BANNER_TAIL_URL)
    suspend fun getHomeBannerList():BaseResp<List<HomeBannerModel>>
    @GET("article/list/{pageIndex}/json")
    suspend fun getArticleListById(@Path("pageIndex") pageIndex:Int):BaseResp<HomePageArticleModel>
    @GET(BaseURL.TOP_RANK_ARTICLE_TAIL_URL)
    suspend fun getTopRankArticles():BaseResp<List<HomeTopRankModel>>
}