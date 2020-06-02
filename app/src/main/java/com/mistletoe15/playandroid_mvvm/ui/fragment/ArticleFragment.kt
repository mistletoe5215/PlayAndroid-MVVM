package com.mistletoe15.playandroid_mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.databinding.FragmentArticleBinding
import com.mistletoe15.playandroid_mvvm.ui.adapter.BannerImageAdapter
import com.mistletoe15.playandroid_mvvm.view_model.HomeBannerViewModel
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_article.*

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment() {
    var  mBinding:FragmentArticleBinding? =null
    private lateinit var homeBannerViewModel: HomeBannerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_article, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addHomeBanner()

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ArticleFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArticleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    private fun addHomeBanner(){
        homeBannerViewModel =  ViewModelProvider(this).get( HomeBannerViewModel::class.java)
        homeBannerViewModel.getHomeBannerList()
        homeBannerViewModel.bannerList.observe(viewLifecycleOwner, Observer {
            my_article_banner.addBannerLifecycleObserver(this)
                .setAdapter(BannerImageAdapter(it))
                .setIndicator( CircleIndicator(context))
                .start()
        })

    }
}
