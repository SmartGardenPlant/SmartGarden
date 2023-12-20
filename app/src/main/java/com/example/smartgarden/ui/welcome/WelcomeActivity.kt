package com.example.smartgarden.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartgarden.MainActivity
import com.example.smartgarden.R
import com.example.smartgarden.databinding.ActivityWelcomeBinding
import com.example.smartgarden.ui.login.LoginActivity
import com.example.smartgarden.ui.signup.SignupActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

//    private val PREFS_NAME = "LoginPrefs"
//    private val KEY_USER_ID = "userId"
//    private val KEY_LOGIN_STATUS = "loginStatus"
//    private val KEY_USER_NAME = "userName"
//    private val KEY_USER_EMAIL = "userEmail"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.signupButton.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

//        // Pengecekan sesi login
//        if (isUserLoggedIn()) {
//            // Pengguna sudah login, arahkan ke MainActivity
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        } else {
//            // Pengguna belum login, lanjutkan dengan proses login
//            startActivity(Intent(this, WelcomeActivity::class.java))
//        }
    }

//    private fun isUserLoggedIn(): Boolean {
//        val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
//        return sharedPreferences.getBoolean(KEY_LOGIN_STATUS, false)
//    }
}