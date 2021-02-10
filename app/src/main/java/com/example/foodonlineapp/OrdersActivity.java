package com.example.foodonlineapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodonlineapp.Adapters.OrdersAdapter;
import com.example.foodonlineapp.Models.OrdersModel;
import com.example.foodonlineapp.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {
    ActivityOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getorder();


        OrdersAdapter adapter = new OrdersAdapter(list,this);
        binding.ordersrecyclerview.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.ordersrecyclerview.setLayoutManager(linearLayoutManager);
    }
}