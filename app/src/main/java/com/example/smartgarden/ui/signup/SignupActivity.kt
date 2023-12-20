package com.example.smartgarden.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.smartgarden.R
import com.example.smartgarden.databinding.ActivitySignupBinding
import com.example.smartgarden.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        val backButton = binding.backButton
        backButton.setOnClickListener {
            onBackPressed()
        }
        setupAction()
    }

    private fun setupAction() {
        binding.btnRegister.setOnClickListener {
            val username = binding.usernameEd.text.toString()
            val email = binding.emailEd.text.toString()
            val password = binding.passwordEd.text.toString()

            //Validasi username
            if (username.isEmpty()){
                binding.usernameEd.error = "Username harus diisi"
                binding.usernameEd.requestFocus()
                return@setOnClickListener
            }
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

            RegisterFirebase(username,email,password)
        }
    }

    private fun RegisterFirebase(username: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registrasi berhasil, simpan nama dan alamat ke Firebase
                    val userId = auth.currentUser?.uid
                    val user = HashMap<String, Any>()
                    user["name"] = username
                    user["address"] = email

                    // Simpan data pengguna ke Firebase
                    if (userId != null) {
                        val userReference = FirebaseDatabase.getInstance().reference.child("users").child(userId)
                        userReference.setValue(user)
                    }

                    Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()

                    // data berhasil disimpan, intent ke Login Activity
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}