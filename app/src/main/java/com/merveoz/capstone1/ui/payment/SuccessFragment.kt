package com.merveoz.capstone1.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.merveoz.capstone1.R
import com.merveoz.capstone1.databinding.FragmentSuccessBinding
import viewBinding


class SuccessFragment : Fragment(R.layout.fragment_success) {

    private val binding by viewBinding (FragmentSuccessBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoHome.setOnClickListener {
            findNavController().navigate(SuccessFragmentDirections.successToHome())
        }
    }
}