package com.example.plugin_scheme

import android.app.Activity
import android.os.Bundle
import androidx.annotation.LayoutRes

open class BasePluginActivityDelegate : IPluginActivity {
    private lateinit var context: Activity

    override fun attach(proxyActivity: Activity) {
        context = proxyActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {

    }

    override fun onStart() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onStop() {

    }

    override fun onDestroy() {

    }

    fun setContentView(@LayoutRes layoutId: Int) {
        context.setContentView(layoutId)
    }
}