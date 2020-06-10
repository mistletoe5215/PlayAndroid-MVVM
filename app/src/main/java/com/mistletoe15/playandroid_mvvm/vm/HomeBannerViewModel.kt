package com.mistletoe15.playandroid_mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mistletoe15.playandroid_mvvm.data.bean.HomeBannerModel
import com.mistletoe15.playandroid_mvvm.data.repository.DataRepository

/**
 * Created by Mistletoe on 2020/5/29
 **/
class HomeBannerViewModel:ViewModel() {
    var bannerList = MutableLiveData<List<HomeBannerModel>>()
    fun getHomeBannerList() = DataRepository.instance.getHomeBannerList(this)
}