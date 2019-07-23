package com.inplayer.android.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.inplayer.android.R
import com.sdk.inplayer.configuration.InPlayer

class AppStartFragment : Fragment() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isUserSignedIn = InPlayer.Account.isAuthenticated()
        
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.appStartFragment, true).build()
        val navController = findNavController()
        
        if (!isUserSignedIn) {
            navController.navigate(
                R.id.action_appStartFragment_to_loginFragment,
                Bundle.EMPTY,
                navOptions
            )
        } else {
            findNavController().navigate(
                R.id.action_appStartFragment_to_paymentFragment,
                Bundle.EMPTY,
                navOptions
            )
        }
    }
}
