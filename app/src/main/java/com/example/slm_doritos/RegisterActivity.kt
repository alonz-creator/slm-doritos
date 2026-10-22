package com.example.slm_doritos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.slm_doritos.databinding.ActivityRegisterBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "RegisterActivity dibuat pertama kali")

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val username = binding.inputUsername.text.toString().trim()
            val email = binding.inputEmail.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()
            val confirmPassword = binding.inputConfirmPassword.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Peringatan")
                    .setMessage("Semua kolom harus diisi!")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Peringatan")
                    .setMessage("Password dan Konfirmasi Password tidak sama!")
                    .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    .show()
                return@setOnClickListener
            }

            // Simpan data registrasi ke SharedPreferences (Penerapan SP)
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("registered_username", username)
            editor.putString("registered_email", email)
            editor.putString("registered_password", password)
            editor.putBoolean("isVerified", false)
            editor.apply()

            Log.e("Register", "Registrasi berhasil untuk $username")

            // Navigasi ke Halaman Verifikasi dengan membawa data input registrasi
            val intent = Intent(this, VerificationActivity::class.java).apply {
                putExtra("EXTRA_USERNAME", username)
                putExtra("EXTRA_EMAIL", email)
                putExtra("EXTRA_PASSWORD", password)
            }
            startActivity(intent)
            finish()
        }

        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: RegisterActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "RegisterActivity dihapus dari stack")
    }
}
