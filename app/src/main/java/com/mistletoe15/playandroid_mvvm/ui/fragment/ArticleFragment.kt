package com.mistletoe15.playandroid_mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.databinding.FragmentArticleBinding
import com.mistletoe15.playandroid_mvvm.ui.adapter.BannerImageAdapter
import com.mistletoe15.playandroid_mvvm.ui.adapter.HomeArticleAdapter
import com.mistletoe15.playandroid_mvvm.vm.HomeArticlePageViewModel
import com.mistletoe15.playandroid_mvvm.vm.HomeBannerViewModel
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.fragment_article.*


/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment() {
    private var  mBinding:FragmentArticleBinding? =null
    private lateinit var homeBannerViewModel: HomeBannerViewModel
    private lateinit var homeArticlePageViewModel: HomeArticlePageViewModel
    private lateinit var articleAdapter: HomeArticleAdapter
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
        addArticleList()
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
        fun newInstance() =
            ArticleFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    //banner初始化
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
    //文章列表初始化
    private fun addArticleList(){
        homeArticlePageViewModel =  ViewModelProvider(this).get(HomeArticlePageViewModel::class.java)
        articleAdapter = HomeArticleAdapter()
        article_rv_list.layoutManager = LinearLayoutManager(context)
        article_rv_list.adapter =  articleAdapter
        homeArticlePageViewModel.livePagedListBuilder.observe(viewLifecycleOwner, Observer {
            articleAdapter.submitList(it)
        })
        article_swipe_refresh.setColorSchemeResources(R.color.colorAccent)
        article_swipe_refresh.setOnRefreshListener(OnRefreshListener {
            homeArticlePageViewModel.livePagedListBuilder.value?.dataSource?.invalidate()
            if(article_swipe_refresh.isRefreshing){
                article_swipe_refresh.isRefreshing = false
                Toast.makeText(activity,"已更新...",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
