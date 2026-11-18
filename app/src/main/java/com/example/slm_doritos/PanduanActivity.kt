package com.example.slm_doritos

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.slm_doritos.databinding.ActivityPanduanBinding

/**
 * Pertemuan 11: Activity Onboarding Screen berbasis ViewPager2 & DotsIndicator
 */
class PanduanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPanduanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanduanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inisialisasi Toolbar dengan Tombol Kembali (Back Navigation)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Panduan Member"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // 2. Inisialisasi daftar Fragment Onboarding dan PanduanFragmentAdapter
        val fragmentsList = listOf(Tutorial1Fragment(), Tutorial2Fragment(), Tutorial3Fragment())
        val adapter = PanduanFragmentAdapter(this, fragmentsList)
        binding.tutorialMessageViewPager.adapter = adapter

        // 3. Menghubungkan DotsIndicator dengan ViewPager2
        binding.dotIndicator.attachTo(binding.tutorialMessageViewPager)
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
