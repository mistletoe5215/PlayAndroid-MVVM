package com.mistletoe15.playandroid_mvvm.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.databinding.ActivityWebViewBinding


/**
 * Created by Mistletoe on 2020/6/2
 **/
class WebViewPage : AppCompatActivity(){
    private var mBinding: ActivityWebViewBinding?=null
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        if(intent?.extras?.containsKey("url")!!){
            mBinding?.webView?.apply {
                settings.javaScriptEnabled = true
                webChromeClient = object : WebChromeClient() {
                    override fun onProgressChanged(view: WebView, newProgress: Int) {
                        super.onProgressChanged(view, newProgress)
                        //TODO:优化加载网页白屏
                    }
                }
                loadUrl(intent?.extras?.getString("url"))
            }
        }
    }
}