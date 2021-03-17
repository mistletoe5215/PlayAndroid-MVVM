package com.mistletoe15.playandroid_mvvm.ui.adapter
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mistletoe.coroutinewrapper.acquireLifecycleScope
import com.mistletoe.coroutinewrapper.acquireObservableScope
import com.mistletoe15.playandroid_mvvm.data.bean.HomeBannerModel
import com.mistletoe15.playandroid_mvvm.ui.activity.WebViewPage
import com.mistletoe15.playandroid_mvvm.utils.ViewUtil.getHttpBitmap
import com.youth.banner.adapter.BannerAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Mistletoe on 2020/6/2
 **/
class BannerImageAdapter(dataList:List<HomeBannerModel>?) : BannerAdapter<HomeBannerModel, BannerImageAdapter.BannerViewHolder>(dataList) {
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

    override fun onBindView(holder: BannerViewHolder?, data: HomeBannerModel?, position: Int, size: Int) {
        holder?.itemView?.context?.acquireLifecycleScope()?.launch {
            val bmpDeferred  = async{
                getHttpBitmap(data?.imagePath)
            }
            (holder.itemView as ImageView).apply {
                setImageBitmap(bmpDeferred.await())
                val intent = Intent(context,  WebViewPage::class.java).apply {
                    putExtra("url",data?.url)
                }
                setOnClickListener{ startActivity(context,intent, Bundle())}
            }
        }
    }

}