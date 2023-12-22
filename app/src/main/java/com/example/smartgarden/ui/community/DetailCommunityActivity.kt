package com.example.smartgarden.ui.community

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.smartgarden.R
import com.example.smartgarden.databinding.ActivityDetailCommunityBinding
import com.example.smartgarden.dummy.Community

class DetailCommunityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCommunityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        val Community = intent.getParcelableExtra<Community>(CommunityFragment.INTENT_PARCELABLE)

        val imageView = findViewById<ImageView>(R.id.imageView_detail_community)
        val tv_user = findViewById<TextView>(R.id.tv_user_detail_community)
        val tv_date = findViewById<TextView>(R.id.tv_date)
        val tv_title = findViewById<TextView>(R.id.tv_title_detail_community)
        val tv_desc_detail = findViewById<TextView>(R.id.tv_desc_detail_community)

        imageView.setImageResource(Community?.photo!!)
        tv_user.text = Community.user
        tv_date.text = Community.date
        tv_title.text = Community.title
        tv_desc_detail.text = Community.detail

        val backButton = binding.backButton
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}