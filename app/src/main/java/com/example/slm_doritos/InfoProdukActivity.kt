package com.example.slm_doritos

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.slm_doritos.databinding.ActivityInfoProdukBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Pertemuan 11: Activity utama pengelola TabLayout dan ViewPager2
 * Menghubungkan 3 Tab Fragment (Info VIP, Syarat, dan Produk)
 */
class InfoProdukActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInfoProdukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inisialisasi Toolbar dengan Tombol Kembali (Back Navigation)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Info & Produk"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // 2. Inisialisasi Adapter FragmentStateAdapter untuk ViewPager2
        val tabsAdapter = InfoProdukTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // 3. Menghubungkan TabLayout dan ViewPager2 menggunakan TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    // Konfigurasi Tab 0: Info VIP
                    tab.text = "Info VIP"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    // Konfigurasi Tab 1: Syarat Booking (dilengkapi Badge Nomor)
                    tab.text = "Syarat"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_settings)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                2 -> {
                    // Konfigurasi Tab 2: Katalog Produk (RecyclerView)
                    tab.text = "Produk"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_layanan)
                }
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
