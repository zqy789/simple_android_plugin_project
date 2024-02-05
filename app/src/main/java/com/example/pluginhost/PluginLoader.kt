package com.example.pluginhost

import android.content.Context
import com.example.plugin_scheme.BasePluginActivity
import com.example.plugin_scheme.IPluginActivity
import com.example.plugin_scheme.PluginConstant
import dalvik.system.DexClassLoader
import java.io.File

class PluginLoader(
    private val context: Context,
) {
    var pluginApkPath:String ? = null
    var dexClassLoader: DexClassLoader? = null

    //将插件apk下载到内部存储空间内
    //已知存apk存储在了内部存储空间./plugins
    fun install(apkName: String, activityName: String): IPluginActivity {
        val pluginRootDir = "${context.filesDir.absolutePath}/${PluginConstant.PLUGIN_DIR}"
        pluginApkPath = File(pluginRootDir, "$apkName.apk").absolutePath
        val nativeLib = File(pluginRootDir, "pluginLib").absolutePath
        val dexOptimizedDirectory = File(pluginRootDir, "dexOut").absolutePath

        dexClassLoader =
            DexClassLoader(pluginApkPath, dexOptimizedDirectory, nativeLib, context.classLoader)
        return dexClassLoader!!.loadClass(activityName).newInstance() as IPluginActivity
    }
}