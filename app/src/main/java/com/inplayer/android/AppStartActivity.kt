package com.inplayer.android

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class AppStartActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        DataBindingUtil.setContentView<com.inplayer.android.databinding.ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        
    }
}
