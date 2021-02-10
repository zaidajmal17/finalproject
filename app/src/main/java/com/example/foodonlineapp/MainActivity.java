package com.example.foodonlineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodonlineapp.Adapters.MainAdapter;
import com.example.foodonlineapp.Models.MainModel;
import com.example.foodonlineapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<MainModel>list = new ArrayList<>();
        list.add(new MainModel(R.drawable.burg,"Burger","35","Chicken Burger With Chicken Patty"));
        list.add(new MainModel(R.drawable.cheese,"Burger","52","Chicken Burger With Chicken Patty and Cheese"));
        list.add(new MainModel(R.drawable.pizbur,"Burger","45","Chicken Burger With Chicken Patty and Cheeses Buns"));
        list.add(new MainModel(R.drawable.pizza,"Burger","95","Chicken pizza With Onions ,Mushrooms"));
        list.add(new MainModel(R.drawable.burg,"Burger","75","Chicken Burger With Chicken Patty"));
        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrdersActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}