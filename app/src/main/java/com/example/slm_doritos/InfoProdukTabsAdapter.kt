package com.example.slm_doritos

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Pertemuan 11: FragmentStateAdapter untuk mengelola penampil Fragment pada ViewPager2
 */
class InfoProdukTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Menentukan jumlah total tab yang tersedia
    override fun getItemCount(): Int = 3

    // Menentukan Fragment yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabInfoLayananFragment()
            1 -> TabSyaratBookingFragment()
            2 -> TabKatalogProdukFragment()
            else -> throw IllegalStateException("Posisi tab tidak valid")
        }
    }
}
