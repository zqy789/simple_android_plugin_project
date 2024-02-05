package com.example.plugin;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.plugin_scheme.BasePluginActivity;

public class PluginActivity extends BasePluginActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
