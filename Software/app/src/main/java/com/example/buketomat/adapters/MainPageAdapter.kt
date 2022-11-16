package com.example.buketomat.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.buketomat.R
import kotlin.reflect.KClass

class MainPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {

    data class FragmentItem(val titleRes: Int, val iconRes: Int, val fragmentClass: KClass<*>)

    val fragmentItems = ArrayList<FragmentItem>()

    fun AddFragment(fragment : FragmentItem)
    {
        fragmentItems.add(fragment);
    }
    override fun getItemCount(): Int {
        return fragmentItems.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentItems[position].fragmentClass.java.newInstance() as Fragment
    }
}
