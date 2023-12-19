package com.example.smartgarden.ui.myplant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartgarden.databinding.FragmentCommunityBinding
import com.example.smartgarden.databinding.FragmentMyplantBinding

class MyPlantFragment : Fragment() {
    private lateinit var binding: FragmentMyplantBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyplantBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    override fun onClick(v: View) {
//
//    }
}