package com.mistletoe15.playandroid_mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mistletoe15.playandroid_mvvm.data.bean.HomeVideoViewConfigModel
import com.mistletoe15.playandroid_mvvm.data.repository.DataRepository

/**
 * Created by Mistletoe on 2020/6/10
 **/
class MyHomeVideoViewModel: ViewModel() {
    var homeVideoConfig = MutableLiveData<HomeVideoViewConfigModel>()
    fun initAndSetVideoConfig() = DataRepository.instance.initAndSetVideoConfig(this)
}