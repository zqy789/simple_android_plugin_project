package com.example.plugin_scheme

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity

interface IPluginActivity {

    fun attach(proxyActivity: ComponentActivity)

    fun onCreate(savedInstanceState: Bundle?)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()
}