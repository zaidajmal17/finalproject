package com.example.foodonlineapp.Activities;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodonlineapp.Adapters.MainAdapter;
import com.example.foodonlineapp.Models.MainModel;
import com.example.foodonlineapp.R;
import com.example.foodonlineapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseDatabase database;
    FirebaseStorage storage;
    MainAdapter mainAdapter;
    ArrayList<MainModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         list = new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        mainAdapter=new MainAdapter(list,this);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(mainAdapter);

        database.getReference().child("recipe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
           if(snapshot.exists()){
               list.clear();

               for(DataSnapshot snapshot1:snapshot.getChildren()){
                   MainModel mainModel = snapshot1.getValue(MainModel.class);
                   mainModel.setDescription(snapshot1.child("description").getValue(String.class));
                   mainModel.setImage(snapshot1.child("image").getValue(String.class));
                   mainModel.setName(snapshot1.child("name").getValue(String.class));
                   mainModel.setPrice(snapshot1.child("price").getValue(String.class));
                   list.add(mainModel);
               }
               mainAdapter.notifyDataSetChanged();
           }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        list.add(new MainModel("1st","Burger","35","Chicken Burger With Chicken Patty"));
//        list.add(new MainModel("1st","Burger","52","Chicken Burger With Chicken Patty and Cheese"));
//        list.add(new MainModel("1st","Burger","45","Chicken Burger With Chicken Patty and Cheeses Buns"));
//        list.add(new MainModel("1st","Burger","95","Chicken pizza With Onions ,Mushrooms"));
//        list.add(new MainModel("1st","Burger","75","Chicken Burger With Chicken Patty"));
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
                startActivity(new Intent(MainActivity.this, OrdersActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}