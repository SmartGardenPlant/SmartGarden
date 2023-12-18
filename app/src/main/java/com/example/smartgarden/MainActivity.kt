package com.example.smartgarden

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.smartgarden.camera.UploadActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Find the CardView with ID card_camera
        val cardCamera: CardView = findViewById(R.id.card_camera)

        // Set up an OnClickListener for the CardView
        cardCamera.setOnClickListener {
            // Start the CameraActivity when the CardView is clicked
            startActivity(Intent(this@MainActivity, UploadActivity::class.java))
        }
    }
}
