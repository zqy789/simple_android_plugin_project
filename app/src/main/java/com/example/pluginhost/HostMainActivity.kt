package com.example.pluginhost

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.plugin_scheme.PluginConstant
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream


class HostMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            downloadApk2FileDir("plugin1.apk") {
               PluginStubActivity.inject(this, "plugin1",
                   "com.example.plugin.PluginActivity")
            }
        }
    }

    private fun downloadApk2FileDir(path: String, loaded: () -> Unit) {
        lifecycleScope.launch(Dispatchers.IO) {
            val rootDir = File(filesDir.absoluteFile, PluginConstant.PLUGIN_DIR)
            if (rootDir.exists().not()) {
                rootDir.mkdir()
            }

            val target = File(rootDir.absoluteFile, "plugin1.apk")
            assets.open(path).copyTo(FileOutputStream(target))
            loaded()
        }
    }

}