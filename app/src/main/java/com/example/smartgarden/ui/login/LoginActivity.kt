package com.example.smartgarden.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.smartgarden.MainActivity
import com.example.smartgarden.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val PREFS_NAME = "LoginPrefs"
    private val KEY_USER_ID = "userId"
    private val KEY_LOGIN_STATUS = "loginStatus"
    private val KEY_USER_NAME = "userName"
    private val KEY_USER_ADDRESS = "userAddress"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference.child("users")

        // Pengecekan sesi login
        if (isUserLoggedIn()) {
            // Pengguna sudah login, arahkan ke MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            // Pengguna belum login, lanjutkan dengan proses login
            setupAction()
        }

        val backButton = binding.backButton
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.emailEd.text.toString()
            val password = binding.passwordEd.text.toString()
            //Validasi email
            if (email.isEmpty()){
                binding.emailEd.error = "Email harus diisi"
                binding.emailEd.requestFocus()
                return@setOnClickListener
            }
            //Validasi format email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailEd.error = "Email tidak valid"
                binding.emailEd.requestFocus()
                return@setOnClickListener
            }
            //Validasi password
            if (password.isEmpty()){
                binding.passwordEd.error = "Password harus diisi"
                binding.passwordEd.requestFocus()
                return@setOnClickListener
            }
            //Validasi format password
            if (password.length < 8){
                binding.passwordEd.error = "Password minimal 8 karakter"
                binding.passwordEd.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password )
        }
    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        // Ambil data pengguna dari Firebase setelah login berhasil
                        getUserDataFromFirebase(userId)
                    }
                } else {
                    Toast.makeText(this, "Login gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun getUserDataFromFirebase(userId: String) {
        database.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userName = snapshot.child("name").value.toString()
                    val userAddress = snapshot.child("address").value.toString()

                    // Simpan sesi login
                    saveLoginSession(userId, userName, userAddress)

                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()

                    // Intent ke MainActivity
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    private fun getUserDataFromFirebase(userId: String) {
//        database.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    val userName = snapshot.child("name").value.toString()
//                    val userEmail = snapshot.child("email").value.toString()
//
//                    // Simpan sesi login
//                    saveLoginSession(userId, userName, userEmail)
//
//                    Toast.makeText(this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT).show()
//
//                    // Intent ke MainActivity
//                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
//
//                    // Pass username and email as arguments to ProfileFragment
//                    val profileFragment = ProfileFragment().apply {
//                        arguments = Bundle().apply {
//                            putString("userName", userName)
//                            putString("userEmail", userEmail)
//                        }
//                    }
//
//                    // Replace with the ProfileFragment
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, profileFragment)
//                        .commit()
//
//                    finish()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@LoginActivity, "Gagal mengambil data pengguna", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

    private fun saveLoginSession(userId: String, userName: String, userAddress: String) {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_USER_ID, userId)
        editor.putBoolean(KEY_LOGIN_STATUS, true)
        editor.putString(KEY_USER_NAME, userName)
        editor.putString(KEY_USER_ADDRESS, userAddress)
        editor.apply()
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_LOGIN_STATUS, false)
    }
}