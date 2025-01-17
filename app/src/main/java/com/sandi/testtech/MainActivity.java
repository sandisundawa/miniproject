package com.sandi.testtech;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sandi.testtech.adapter.DataAdapter;
import com.sandi.testtech.databinding.ActivityMainBinding;
import com.sandi.testtech.model.DataExample;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ComponentActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    private List<DataExample> filteredList = new ArrayList<>();
    private List<DataExample> originalList = new ArrayList<>();

    private DataAdapter adapter;

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
                binding.loading.setVisibility(View.GONE);
                adapter = new DataAdapter(data, this);
                binding.rvList.setAdapter(adapter);
                binding.rvList.setLayoutManager(
                        new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

                binding.etSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        filterList(charSequence.toString(), data);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            } else {
                binding.loading.setVisibility(View.GONE);
                Toast.makeText(this, "data kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterList(String query, List<DataExample> dataExampleList) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            for (DataExample data : dataExampleList) {
                if (data.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(data);
                }
            }
        }
        adapter.refreshData(filteredList);
        adapter.notifyDataSetChanged();
    }

}
