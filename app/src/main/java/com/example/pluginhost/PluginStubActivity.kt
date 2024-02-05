package com.example.pluginhost

import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.plugin_scheme.IPluginActivity

class PluginStubActivity : ComponentActivity() {
    private var pluginResources: Resources? = null
    private var pluginAssetManager: AssetManager? =null
    private val pluginLoader = PluginLoader(this)
    private var basePluginActivityDelegate: IPluginActivity? = null

    companion object {
        fun inject(context: Context, pluginName: String, activityName: String) {
            val intent = Intent(context, PluginStubActivity::class.java)
            intent.putExtra("pluginName", pluginName)
            intent.putExtra("activityName", activityName)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pluginName = intent.getStringExtra("pluginName")
        val activityName = intent.getStringExtra("activityName")
        if (pluginName?.isEmpty() == true || activityName?.isEmpty() == true) {
            finish()
            return
        }
        basePluginActivityDelegate = pluginLoader.install(pluginName!!, activityName!!)
        covertResource()

        basePluginActivityDelegate!!.attach(this)
        basePluginActivityDelegate!!.onCreate(savedInstanceState)
    }

    private fun covertResource() {
        try {
            // 生成 AssetManager
            pluginAssetManager = AssetManager::class.java.newInstance()
            // 添加插件 apk 路径
            val addAssetPathMethod =
                pluginAssetManager?.javaClass?.getMethod("addAssetPath", String::class.java)
            addAssetPathMethod?.invoke(pluginAssetManager, pluginLoader.pluginApkPath)
        } catch (e: Exception) {
            return
        }
        // 生成插件资源
        pluginResources = Resources(
            pluginAssetManager,
            super.getResources().displayMetrics,
            super.getResources().configuration
        )

        val newTheme = pluginResources?.newTheme()
        newTheme?.setTo(super.getTheme())
    }

    override fun getResources(): Resources {
        return pluginResources ?: super.getResources()
    }

    override fun getAssets(): AssetManager {
        return pluginAssetManager ?: super.getAssets()
    }

    override fun getClassLoader(): ClassLoader {
        return pluginLoader.dexClassLoader ?: super.getClassLoader()
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