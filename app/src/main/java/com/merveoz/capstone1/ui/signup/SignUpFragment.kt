package com.merveoz.capstone1.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.merveoz.capstone1.R
import com.merveoz.capstone1.common.gone
import com.merveoz.capstone1.common.visible
import com.merveoz.capstone1.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding (FragmentSignUpBinding::bind)

    private val viewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        with(binding) {
            btnSignup.setOnClickListener {
                viewModel.signUp(
                    etMail.text.toString(),
                    etPassword.text.toString()
                )
            }
            tvLogin.setOnClickListener {
                findNavController().navigate(SignUpFragmentDirections.signUpToLogIn())
            }
        }
    }

    private fun observeData() = with(binding) {
        viewModel.signUpState.observe(viewLifecycleOwner) { state ->
            when (state) {
                SignUpState.Loading -> progressBar.visible()

                is SignUpState.GoToHome -> {
                    progressBar.gone()
                    findNavController().navigate(SignUpFragmentDirections.signUpToMainGraph())
                }

                is SignUpState.ShowPopUp -> {
                    progressBar.gone()
                    Snackbar.make(requireView(), state.errorMessage, 1000).show()
                }
            }
        }
    }
}