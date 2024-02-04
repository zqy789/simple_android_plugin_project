package com.example.pluginhost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plugin_scheme.BasePluginActivityDelegate

class PluginStubActivity : AppCompatActivity() {
    private val pluginLoader = PluginLoader(this)
    private var basePluginActivityDelegate: BasePluginActivityDelegate? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pluginName = intent.getStringExtra("pluginName")
        val activityName = intent.getStringExtra("activityName")
        if (pluginName?.isEmpty() == true || activityName?.isEmpty() == true) {
            finish()
            return
        }
        basePluginActivityDelegate = pluginLoader.install(pluginName!!, activityName!!)
        basePluginActivityDelegate!!.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        basePluginActivityDelegate?.onStart()
    }


    override fun onResume() {
        super.onResume()
        basePluginActivityDelegate?.onResume()
    }

    override fun onPause() {
        super.onPause()
        basePluginActivityDelegate?.onPause()
    }

    override fun onStop() {
        super.onStop()
        basePluginActivityDelegate?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        basePluginActivityDelegate?.onDestroy()
    }

}