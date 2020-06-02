package com.mistletoe15.playandroid_mvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mistletoe15.playandroid_mvvm.R
import com.mistletoe15.playandroid_mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mainBinding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val  navHostFragment:NavHostFragment =  supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        mainBinding?.navView?.let {  NavigationUI.setupWithNavController(it,navHostFragment.navController)}
    }
    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this,R.id.nav_view).navigateUp()
    }
}
