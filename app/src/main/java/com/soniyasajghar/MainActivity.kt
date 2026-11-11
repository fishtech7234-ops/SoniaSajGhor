package com.soniyasajghar

import android.app.Activity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private val micPermission = Manifest.permission.RECORD_AUDIO
    private val requestCode = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(30, 50, 30, 30)

        val title = TextView(this)
        title.text = "Sonia Saj Ghor"
        title.textSize = 26f

        val info = TextView(this)
        info.text = "Microphone permission is required."
        info.textSize = 18f

        val startButton = Button(this)
        startButton.text = "Allow Microphone"

        startButton.setOnClickListener {
            if (checkSelfPermission(micPermission) ==
                PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(
                    this,
                    "Microphone permission already granted",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                requestPermissions(
                    arrayOf(micPermission),
                    requestCode
                )
            }
        }

        layout.addView(title)
        layout.addView(info)
        layout.addView(startButton)

        setContentView(layout)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )

        if (requestCode == this.requestCode &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(
                this,
                "Microphone permission granted",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
