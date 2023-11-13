package com.merveoz.capstone1.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.merveoz.capstone1.R
import com.merveoz.capstone1.common.gone
import com.merveoz.capstone1.common.visible
import com.merveoz.capstone1.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import viewBinding

@AndroidEntryPoint
class LogInFragment : Fragment(R.layout.fragment_log_in) {

    private val binding by viewBinding (FragmentLogInBinding::bind)

    private val viewModel: LogInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            btnLogIn.setOnClickListener{
                viewModel.logIn(
                    etMail.text.toString(),
                    etPassword.text.toString()
                )
            }

            tvSignup.setOnClickListener{
                findNavController().navigate(LogInFragmentDirections.logInToSignUp())
            }
        }

        observeData()
    }

    private fun observeData() = with(binding) {
        viewModel.logInState.observe(viewLifecycleOwner) { state ->
            when (state) {
                LogInState.Loading -> progressBar.visible()

                is LogInState.GoToHome -> {
                    progressBar.gone()
                    findNavController().navigate(LogInFragmentDirections.logInToMainGraph())
                }

                is LogInState.ShowPopUp -> {
                    progressBar.gone()
                    Snackbar.make(requireView(), state.errorMessage, 1000).show()
                }
            }
        }
    }
}