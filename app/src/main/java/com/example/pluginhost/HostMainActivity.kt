package com.example.pluginhost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HostMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_main)

        findViewById<Button>(R.id.button).setOnClickListener {

        }
    }
}