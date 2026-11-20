package com.soniyasajghar.sender

import android.app.Activity
import android.os.Bundle
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : Activity() {

    private lateinit var permissionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(24, 40, 24, 24)

        permissionButton = Button(this)
        permissionButton.typeface = Typeface.create("cursive", Typeface.NORMAL)

        layout.addView(permissionButton)
        setContentView(layout)

        updatePermissionButton()

        permissionButton.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    100
                )
            }
        }

        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)
            == PackageManager.PERMISSION_GRANTED) {
            startMicrophoneService()
        }
    }

    private fun updatePermissionButton() {
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)
            == PackageManager.PERMISSION_GRANTED) {
            permissionButton.text = "Microphone Access Granted"
            permissionButton.isEnabled = false
        } else {
            permissionButton.text = "Allow Microphone Access"
            permissionButton.isEnabled = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100) {
            updatePermissionButton()

            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startMicrophoneService()
            }
        }
    }

    private fun startMicrophoneService() {
        val serviceIntent = Intent(this, MicrophoneService::class.java)

        try {
            if (android.os.Build.VERSION.SDK_INT >= 26) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
