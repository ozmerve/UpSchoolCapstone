package com.merveoz.capstone1.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.merveoz.capstone1.R
import com.merveoz.capstone1.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding(FragmentSplashBinding::bind)

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        viewModel.splashState.observe(viewLifecycleOwner) { state ->
            when (state) {
                SplashState.GoToSignIn -> {
                    findNavController().navigate(SplashFragmentDirections.splashToLogIn())
                }

                SplashState.GoToHome -> {
                    findNavController().navigate(SplashFragmentDirections.splashToMainGraph())
                }
            }
        }
    }
}