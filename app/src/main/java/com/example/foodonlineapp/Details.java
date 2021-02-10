package com.example.foodonlineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        DBHelper helper = new DBHelper(this);
        binding.btnordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInsert = helper.insertorder(binding.etnamebox.getText().toString(),binding.etphonebox.getText().toString(),
                price,
                        image,
                        name,
                        des,
                        Integer.parseInt(binding.quantity.getText().toString())
                );
                if(isInsert){
                    Toast.makeText(Details.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(Details.this,"Not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
}