package com.sandi.testtech;

import android.os.Bundle;

import androidx.activity.ComponentActivity;

import com.sandi.testtech.databinding.ActivityMainBinding;

public class MainActivity extends ComponentActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}
