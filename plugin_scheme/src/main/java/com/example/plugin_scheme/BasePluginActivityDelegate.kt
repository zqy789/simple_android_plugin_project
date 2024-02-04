package com.example.plugin_scheme

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

open class BasePluginActivityDelegate :AppCompatActivity() {
    private lateinit var context: Activity

    fun attach(proxyActivity: Activity) {
        context = proxyActivity
    }

}