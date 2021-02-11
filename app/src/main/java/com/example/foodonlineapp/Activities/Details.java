package com.example.foodonlineapp.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodonlineapp.databinding.ActivityDetailsBinding;

public class Details extends AppCompatActivity {
    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       final int image = getIntent().getIntExtra("image",0);
        final int price = Integer.parseInt(getIntent().getStringExtra("price"));
        final String name = getIntent().getStringExtra("name");
        final String des= getIntent().getStringExtra("description");
        binding.ivdetail.setImageResource(image);
        binding.pricelabel.setText(String.format("%d",price));
        binding.etnamebox.setText(name);
        binding.detaildes.setText(des);


    }
}