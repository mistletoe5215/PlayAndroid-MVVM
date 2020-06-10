package com.mistletoe15.playandroid_mvvm.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.data.bean.HeaderViewConfigModel
import com.mistletoe15.playandroid_mvvm.data.bean.HomeVideoViewConfigModel
import com.mistletoe15.playandroid_mvvm.data.net.ApiService
import com.mistletoe15.playandroid_mvvm.data.net.RetrofitFactory
import com.mistletoe15.playandroid_mvvm.data.net.handled
import com.mistletoe15.playandroid_mvvm.vm.HeaderViewModel
import com.mistletoe15.playandroid_mvvm.vm.HomeBannerViewModel
import com.mistletoe15.playandroid_mvvm.vm.MyHomeVideoViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Mistletoe on 2020/6/8
 **/
class DataRepository private constructor() {
    companion object {
        val instance: DataRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DataRepository()
        }
    }
    fun getHomeHeaderConfig(context:Context,mHeaderViewModel: HeaderViewModel){
        mHeaderViewModel.headerConfig.value = HeaderViewConfigModel(
            context.resources.getString(R.string.app_name) ,
            context.resources.getColor(R.color.transparent),
            enableLeft = true,
            enableRight = false,
            leftImage  = context.resources.getDrawable(R.drawable.drawer),
            rightImage =  null,
            onLeftViewClickListener = {
                Toast.makeText(context,"打开抽屉页面",Toast.LENGTH_SHORT).show()
            },
            onRightViewClickListener ={

            }
        )
    }
    fun getHomeBannerList(mHomeBannerViewModel: HomeBannerViewModel){
        mHomeBannerViewModel.viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitFactory.instance.getService(ApiService::class.java)
                        .getHomeBannerList().handled()
                }
                mHomeBannerViewModel.bannerList.value = data
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("请求失败", "${e.message}")
            }
        }
    }
    fun initAndSetVideoConfig(myHomeVideoViewModel: MyHomeVideoViewModel){
        myHomeVideoViewModel.homeVideoConfig.value = HomeVideoViewConfigModel("android.resource://com.mistletoe15.playandroid_mvvm/${R.raw.ggz}")
    }
}