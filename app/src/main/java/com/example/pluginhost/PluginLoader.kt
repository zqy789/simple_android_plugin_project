package com.example.pluginhost

import android.content.Context
import com.example.plugin_scheme.BasePluginActivityDelegate
import dalvik.system.DexClassLoader
import java.io.File

class PluginLoader(private val context: Context) {
    private var dexClassLoader: DexClassLoader? = null

    //将插件apk下载到内部存储空间内
    //已知存apk存储在了内部存储空间./plugins
    fun install(apkName: String, activityName: String): BasePluginActivityDelegate {
        val pluginRootDir = "${context.filesDir.absolutePath}/plugin"
        val pluginApkPath = File(pluginRootDir, "$apkName.apk").absolutePath
        val nativeLib = File(pluginRootDir, "pluginLib").absolutePath
        val dexOptimizedDirectory = File(pluginRootDir, "pluginLib").absolutePath

        dexClassLoader =
            DexClassLoader(pluginApkPath, dexOptimizedDirectory, nativeLib, context.classLoader)
        return dexClassLoader!!.loadClass(activityName).newInstance() as BasePluginActivityDelegate
    }
}