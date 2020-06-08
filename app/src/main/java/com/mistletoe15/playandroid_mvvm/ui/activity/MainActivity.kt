package com.mistletoe15.playandroid_mvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.databinding.ActivityMainBinding
import com.mistletoe15.playandroid_mvvm.ui.widget.HeaderView
import com.mistletoe15.playandroid_mvvm.vm.HeaderViewModel
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var mainBinding:ActivityMainBinding?=null
    private var mHeaderViewModel by Delegates.notNull<HeaderViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mHeaderViewModel = ViewModelProvider(this).get(HeaderViewModel::class.java)
        mHeaderViewModel.getHomeHeaderConfig(applicationContext)
        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            HeaderView(applicationContext).setHeaderConfig(mHeaderViewModel.headerConfig.value).let { customView = it }
        }
        val  navHostFragment:NavHostFragment =  supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        mainBinding?.navView?.let {  NavigationUI.setupWithNavController(it,navHostFragment.navController)}
    }
    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this,R.id.nav_view).navigateUp()
    }
}
