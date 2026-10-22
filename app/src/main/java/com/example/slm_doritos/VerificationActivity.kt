package com.example.slm_doritos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.slm_doritos.databinding.ActivityVerificationBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "VerificationActivity dibuat pertama kali")

        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("EXTRA_USERNAME") ?: "-"
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: "-"
        val password = intent.getStringExtra("EXTRA_PASSWORD") ?: "-"

        // Tampilkan data yang diinput di halaman registrasi
        binding.tvResultUsername.text = username
        binding.tvResultEmail.text = email
        binding.tvResultPassword.text = password

        binding.btnVerify.setOnClickListener {
            // Update status verifikasi di SharedPreferences (Penerapan SP)
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            sharedPref.edit().apply {
                putBoolean("isVerified", true)
                putString("username", username)
                apply()
            }

            Log.e("Verification", "Akun $username berhasil diverifikasi")

            MaterialAlertDialogBuilder(this)
                .setTitle("Verifikasi Berhasil")
                .setMessage("Akun Anda telah berhasil diverifikasi. Silakan login menggunakan akun tersebut.")
                .setPositiveButton("Login") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setCancelable(false)
                .show()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: VerificationActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "VerificationActivity dihapus dari stack")
    }
}
