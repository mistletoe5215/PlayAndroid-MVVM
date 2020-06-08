package com.mistletoe15.playandroid_mvvm.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistletoe15.playandroid_mvvm.data.bean.HomeBannerBean
import com.mistletoe15.playandroid_mvvm.data.net.ApiService
import com.mistletoe15.playandroid_mvvm.data.net.RetrofitFactory
import com.mistletoe15.playandroid_mvvm.data.net.handled
import com.mistletoe15.playandroid_mvvm.data.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Mistletoe on 2020/5/29
 **/
class HomeBannerViewModel:ViewModel() {
    var bannerList = MutableLiveData<List<HomeBannerBean>>()
    fun getHomeBannerList() = DataRepository.instance.getHomeBannerList(this)
}