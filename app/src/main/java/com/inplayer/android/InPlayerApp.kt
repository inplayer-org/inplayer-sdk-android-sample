package com.inplayer.android

import android.app.Application
import com.sdk.inplayer.configuration.InPlayer

class InPlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        InPlayer.initialize(
            InPlayer.Configuration.Builder(this, "7ad8a510-b720-4a18-aa38-0260e5fd1cb2")
                .withEnvironment(InPlayer.EnvironmentType.STAGING)
                .build()
        )
    }
}