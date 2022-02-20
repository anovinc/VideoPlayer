package com.example.videoplayer.ui.authentification

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.videoplayer.R
import com.example.videoplayer.databinding.FragmentUserLoginBinding
import com.example.videoplayer.extensions.onClick
import com.example.videoplayer.extensions.visible
import com.example.videoplayer.ui.base.BaseFragment
import com.example.videoplayer.ui.authentification.viewmodels.UserLoginViewModel
import com.google.firebase.firestore.core.View
import org.koin.android.ext.android.inject

class UserLoginFragment : BaseFragment<FragmentUserLoginBinding>() {
    private val viewModel : UserLoginViewModel by inject()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserLoginBinding
        get() = FragmentUserLoginBinding::inflate

    override fun onPostViewCreated() {
        initializeListeners()
    }

    private fun initializeListeners() {
        with(binding) {
            tvRegister.onClick {
                goToRegistration()
            }
            btnLogin.onClick {
                loginUser(getEmail(), getPassword())
            }
            btnAdmin.onClick {
                goToAdminFragment()
            }
        }
    }

    private fun goToRegistration() {
        findNavController().navigate(R.id.action_userLoginFragment_to_userRegistrationFragment)
    }

    private fun isMailOrPasswordEmpty(email: String, password: String) =
        viewModel.isMailOrPasswordEmpty(email, password)

    private fun getEmail() = binding.usernameInput.text.toString()
    private fun getPassword() = binding.passwordInput.text.toString()

    private fun loginUser(email: String, password: String) {
        if (isMailOrPasswordEmpty(email, password)) {
            viewModel.loginUser(email, password) {
                if (it) {
                    binding.progressBar.visible()
                    goForward()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Prijava nije uspijela, pokušajte ponovo",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        else {
            Toast.makeText(requireContext(),"Ostavili ste prazna polja, sva polja moraju biti ispunjena",Toast.LENGTH_SHORT).show()
        }
    }

    private fun goForward() {
        findNavController().navigate(R.id.action_userLoginFragment_to_videoHostFragment)
    }

    private fun goToAdminFragment() {
        findNavController().navigate(R.id.action_userLoginFragment_to_adminFragment)
    }
}