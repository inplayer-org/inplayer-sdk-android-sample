package com.inplayer.android

import android.app.Application
import com.sdk.inplayer.configuration.InPlayer

class InPlayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        InPlayer.initialize(
            InPlayer.Configuration.Builder(this, "19d4ecf5-b076-4261-9c4d-517e6c0c9eff")
                .withEnvironment(InPlayer.EnvironmentType.STAGING)
                .build()
        )
    }
}