package com.example.videoplayer.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.videoplayer.R
import com.example.videoplayer.databinding.FragmentAdminBinding
import com.example.videoplayer.ui.authentification.viewmodels.UserLoginViewModel
import com.example.videoplayer.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class AdminFragment : BaseFragment<FragmentAdminBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAdminBinding
        get() = FragmentAdminBinding::inflate

    private val viewModel: UserLoginViewModel by inject()

    override fun onPostViewCreated() {
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        with(binding) {
            btnAdminLogin.setOnClickListener {
                if(this.usernameInput.text.toString().isNotEmpty() && this.passwordInput.text.toString().isNotEmpty()) {
                    viewModel.loginUser(this.usernameInput.text.toString(), this.passwordInput.text.toString()) {
                        if (it) {
                            goForward()
                        } else {
                            Toast.makeText(requireContext(), "Prijava nije uspijela, pokušajte ponovno", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                else {
                    Toast.makeText(requireContext(), "Ostavili ste prazna polja, sva polja moraju biti ispunjena", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun goForward() {
        findNavController().navigate(R.id.action_adminFragment_to_adminVideosFragment)
    }
}