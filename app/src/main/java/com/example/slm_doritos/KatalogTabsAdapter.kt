package com.example.slm_doritos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class KatalogTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabInfoLayananFragment()
            1 -> TabSyaratBookingFragment()
            2 -> TabKatalogProdukFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}
