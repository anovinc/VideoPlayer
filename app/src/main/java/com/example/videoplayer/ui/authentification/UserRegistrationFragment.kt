package com.example.videoplayer.ui.authentification

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.videoplayer.R
import com.example.videoplayer.databinding.FragmentUserRegistrationBinding
import com.example.videoplayer.extensions.onClick
import com.example.videoplayer.ui.base.BaseFragment
import com.example.videoplayer.ui.authentification.viewmodels.UserRegistrationViewModel
import org.koin.android.ext.android.inject

class UserRegistrationFragment : BaseFragment<FragmentUserRegistrationBinding>() {
    private val viewModel: UserRegistrationViewModel by inject()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUserRegistrationBinding
        get() = FragmentUserRegistrationBinding::inflate

    override fun onPostViewCreated() {
        initializeListeners()
    }

    private fun initializeListeners() {
        with(binding) {
            btnRegister.onClick {
                registerUser(getEmail(), getPassword())
            }
        }
    }

    private fun isMailOrPasswordEmpty(email: String, password: String) =
        viewModel.isMailOrPasswordEmpty(email, password)

    private fun registerUser(email: String, password: String) {
        if (isMailOrPasswordEmpty(email, password)) {
            viewModel.registerUser(email, password) {
                if (it) {
                    goToLogin()
                } else {
                    Toast.makeText(
                        context,
                        "Registracija nije uspijela, provjerite duljinu lozinke(MIN. 6 znakova) ili ispravnost email adrese.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            Toast.makeText(
                context,
                "Ostavili ste prazna polja, sva polja moraju biti ispunjena",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun goToLogin() {
        findNavController().navigate(R.id.action_userRegistrationFragment_to_userLoginFragment)
    }

    private fun getEmail() = binding.usernameInput.text.toString()
    private fun getPassword() = binding.passwordInput.text.toString()

}