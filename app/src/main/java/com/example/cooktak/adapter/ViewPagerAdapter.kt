package com.example.cooktak.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.cooktak.ui.main.barcode.BarcodeFragment
import com.example.cooktak.ui.main.community.CommunityFragment
import com.example.cooktak.ui.main.SearchFragment

class ViewPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(p0: Int): Fragment {
        return when (p0 % 3) {
            0 -> BarcodeFragment()
            1 -> SearchFragment()
            2 -> CommunityFragment()
            else -> null as Fragment
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

}