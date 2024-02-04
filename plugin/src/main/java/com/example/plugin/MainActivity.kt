package com.example.plugin

import android.os.Bundle
import com.example.plugin_scheme.BasePluginActivityDelegate

class MainActivity : BasePluginActivityDelegate() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}