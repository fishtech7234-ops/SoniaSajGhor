package com.soniyasajghar.sender

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MicrophoneService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
