package com.sandi.testtech;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sandi.testtech.adapter.DataAdapter;
import com.sandi.testtech.databinding.ActivityMainBinding;

public class MainActivity extends ComponentActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.fetchListData();

        observeListData();
    }

    private void observeListData() {
        Log.d("kesini", "ya");
        viewModel.listData.observe(this,  data -> {
            Log.d("kesini", data.toString());
            if (data != null) {
                binding.rvList.setAdapter(new DataAdapter(data, this));
                binding.rvList.setLayoutManager(
                        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            }
        });
    }

}
