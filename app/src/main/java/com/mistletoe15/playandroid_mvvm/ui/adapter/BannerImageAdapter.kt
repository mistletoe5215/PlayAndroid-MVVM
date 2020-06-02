package com.mistletoe15.playandroid_mvvm.ui.adapter
import android.graphics.Bitmap
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.mistletoe15.playandroid_mvvm.bean.HomeBannerBean
import com.mistletoe15.playandroid_mvvm.utils.ViewUtil.getHttpBitmap
import com.mistletoe15.playandroid_mvvm.utils.doAsyncJobThen2UI
import com.youth.banner.adapter.BannerAdapter
import kotlinx.coroutines.Job

/**
 * Created by Mistletoe on 2020/6/2
 **/
class BannerImageAdapter(dataList:List<HomeBannerBean>?) : BannerAdapter<HomeBannerBean, BannerImageAdapter.BannerViewHolder>(dataList) {
     inner class BannerViewHolder(itemView: ImageView) : RecyclerView.ViewHolder(itemView)
     override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
         val imageView = ImageView(parent?.context)
         //注意，必须设置为match_parent，这个是viewpager2强制要求的
         imageView.layoutParams = ViewGroup.LayoutParams(
             ViewGroup.LayoutParams.MATCH_PARENT,
             ViewGroup.LayoutParams.MATCH_PARENT
         )
         imageView.scaleType = ImageView.ScaleType.CENTER_CROP
         return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: HomeBannerBean?, position: Int, size: Int) {
        var bmp:Bitmap? = null
        doAsyncJobThen2UI({
            bmp = getHttpBitmap(data?.imagePath)
        },{
            (holder?.itemView as ImageView).setImageBitmap(bmp)
        })
    }

}