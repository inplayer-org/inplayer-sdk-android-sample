package com.inplayer.android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.inplayer.android.R
import com.inplayer.android.databinding.FragmentLoginBinding
import com.inplayer.android.uicommon.afterTextChanged
import com.inplayer.android.uicommon.changeEnabledStateToChildViews
import com.inplayer.android.uicommon.getTrimmedText
import com.sdk.inplayer.callback.InPlayerCallback
import com.sdk.inplayer.configuration.InPlayer
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    
    lateinit var binding: FragmentLoginBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etEmail.afterTextChanged { setProperButtonState() }
        binding.etPassword.afterTextChanged { setProperButtonState() }
        binding.btnLogin.setOnClickListener {
            attemptToLogIn()
        }
    }
    
    private fun attemptToLogIn() {
        val email = binding.etEmail.getTrimmedText()
        val pwd = binding.etPassword.getTrimmedText()
        
        binding.btnLogin.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
        binding.placeholder.changeEnabledStateToChildViews(false)
        InPlayer.Account.authenticate(email, pwd, InPlayerCallback { inPlayerUser, error ->
            binding.placeholder.changeEnabledStateToChildViews(true)
            binding.btnLogin.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            if (error == null) {
                navigateToPaymentPage()
            } else {
                Snackbar.make(
                    binding.root,
                    "Error: ${error.errorsList}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
    }
    
    private fun setProperButtonState() {
        val email = binding.etEmail.getTrimmedText()
        val pwd = binding.etPassword.getTrimmedText()
        binding.btnLogin.isEnabled = email.isNotEmpty() && pwd.isNotEmpty()
    }
    
    private fun navigateToPaymentPage() {
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.appStartFragment, true).build()
        findNavController().navigate(
            R.id.action_loginFragment_to_paymentFragment,
            Bundle.EMPTY,
            navOptions
        )
    }
}
