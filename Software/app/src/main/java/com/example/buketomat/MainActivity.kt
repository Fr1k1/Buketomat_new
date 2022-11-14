package com.example.buketomat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.buketomat.adapters.MainPageAdapter
import com.example.buketomat.fragments.Orders
import com.example.buketomat.fragments.Test1
import com.example.buketomat.fragments.test2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNavigation()
    }

    private fun createNavigation()
    {
        tabLayout= findViewById(R.id.tabs)
        viewPager= findViewById(R.id.viewpager)

        val mainPageAdapter = MainPageAdapter(supportFragmentManager, lifecycle)
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.tab1,R.drawable.ic_launcher_foreground,Test1::class)) //change this to real fragments not test ones
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.orders,R.drawable.ic_baseline_shopping_bag_24,Orders::class)) //adds orders tab

        viewPager.adapter = mainPageAdapter

        TabLayoutMediator(tabLayout,viewPager){
                tab,position ->
            tab.setText(mainPageAdapter.fragmentItems[position].titleRes)
            tab.setIcon(mainPageAdapter.fragmentItems[position].iconRes)
        }.attach()
    }


}