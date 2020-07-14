package com.mistletoe15.playandroid_mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mistletoe15.playandroid_mvvm.data.bean.HomeTopRankModel
import com.mistletoe15.playandroid_mvvm.data.repository.DataRepository

/**
 * Created by Mistletoe on 2020/7/14
 **/
class HomeTopRankViewModel:ViewModel() {
    var errorMessage = MutableLiveData<String>()
    var topRankArticleList = MutableLiveData<List<HomeTopRankModel>>()
    fun getHomeTopRankArticleList() = DataRepository.instance.getHomeTopRankArticleList(this)
}