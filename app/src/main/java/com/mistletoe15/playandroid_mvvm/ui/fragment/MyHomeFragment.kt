package com.mistletoe15.playandroid_mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.databinding.FragmentMyHomeBinding
import com.mistletoe15.playandroid_mvvm.vm.MyHomeVideoViewModel
import kotlinx.android.synthetic.main.fragment_my_home.*

/**
 * Created by Mistletoe on 2020/6/2
 **/
class MyHomeFragment : Fragment(){
    private var mBinding:FragmentMyHomeBinding?=null
    private lateinit var myHomeVideoViewModel: MyHomeVideoViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_my_home, container, false)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MyHomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
    private fun initView(){
        myHomeVideoViewModel = ViewModelProvider(this).get(MyHomeVideoViewModel::class.java)
        myHomeVideoViewModel.initAndSetVideoConfig()
        myHomeVideoViewModel.homeVideoConfig.observe(viewLifecycleOwner, Observer {
           my_home_video_view.apply {
               setVideoPath(it.videoPath)
               start()
               setOnPreparedListener {
                   it.setVolume(0f,0f)
               }
               setOnCompletionListener {
                   start()
               }
           }
        })
    }
}