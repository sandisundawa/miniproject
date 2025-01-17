package com.sandi.testtech;

import android.os.Bundle;

import androidx.activity.ComponentActivity;

import com.orhanobut.hawk.Hawk;
import com.sandi.testtech.databinding.ActivityDetailBinding;
import com.sandi.testtech.model.DataExample;

public class DetailActivity extends ComponentActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DataExample data = Hawk.get("contentData");

        binding.tvIdValue.setText(data.getId().toString());
        binding.tvUserIdValue.setText(data.getUserId().toString());
        binding.tvTitleValue.setText(data.getTitle());
        binding.tvBodyValue.setText(data.getBody());
    }
}
