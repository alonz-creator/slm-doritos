package com.example.slm_doritos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.slm_doritos.databinding.ActivityLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("onCreate", "LoginActivity dibuat pertama kali")

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.inputUsername.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Simpan status sesi ke SharedPreferences
                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Peringatan")
                    .setMessage("Username dan Password tidak boleh kosong!")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        binding.btnRegisterNav.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: LoginActivity terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "LoginActivity dihapus dari stack")
    }
}
