package com.mistletoe15.playandroid_mvvm.vm

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mistletoe15.playandroid_mvvm.data.bean.HeaderViewConfigModel
import com.mistletoe15.playandroid_mvvm.data.repository.DataRepository

/**
 * Created by Mistletoe on 2020/6/8
 **/
class HeaderViewModel: ViewModel() {
    var headerConfig = MutableLiveData<HeaderViewConfigModel>()
    fun getHomeHeaderConfig(context: Context){
        DataRepository.instance.getHomeHeaderConfig(context,this)
    }
}
