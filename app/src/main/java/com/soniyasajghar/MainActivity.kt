package com.soniyasajghar

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(24, 30, 24, 20)

        val title = TextView(this)
        title.text = "Sonia Saj Ghor"
        title.textSize = 26f
        layout.addView(title)

        fun addDevice(name: String) {
            val heading = TextView(this)
            heading.text = name
            heading.textSize = 22f
            layout.addView(heading)

            val status = TextView(this)
            status.text = "Status: Disconnected"
            layout.addView(status)

            val connect = Button(this)
            connect.text = "Connect"
            layout.addView(connect)

            connect.setOnClickListener {
                if (connect.text == "Connect") {
                    connect.text = "Disconnect"
                    status.text = "Status: Connected (Demo)"
                } else {
                    connect.text = "Connect"
                    status.text = "Status: Disconnected"
                }
            }

            val audio = Button(this)
            audio.text = "Audio ON"
            layout.addView(audio)

            audio.setOnClickListener {
                audio.text = if (audio.text == "Audio ON") "Audio OFF" else "Audio ON"
            }
        }

        addDevice("Samsung M20")
        addDevice("Redmi 12")

        setContentView(layout)
    }
}
