package com.example.foodonlineapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodonlineapp.Models.MainModel;
import com.example.foodonlineapp.databinding.ActivityDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Data extends AppCompatActivity {
    ActivityDataBinding binding;
    Uri SelectedImage;
    FirebaseDatabase database;
    FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public void btnSelectImage(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 45);
    }

    public void btnuploadactivity(View view) {
     String name = binding.etname.getText().toString().trim();
     String des = binding.etdes.getText().toString().trim();
     String price = binding.etprice.getText().toString().trim();

     if(name.isEmpty()){
         binding.etname.setError("field is empty");
     }else if(des.isEmpty()){
         binding.etdes.setError("field is empty");
     }else if(price.isEmpty()){
         binding.etprice.setError("field is empty");
     }
     else{
         if(SelectedImage!=null){
             StorageReference reference=storage.getReference().child("Dishes").child(SelectedImage+"");
             reference.putFile(SelectedImage).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String ImageUrl=uri.toString();
                            String name = binding.etname.getText().toString().trim();
                            String price = binding.etprice.getText().toString().trim();
                            String des = binding.etdes.getText().toString().trim();

                            MainModel m=new MainModel(ImageUrl,name,price,des);

                            database.getReference()
                                    .child("recipe")
                                    .push()
                                    .setValue(m)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(Data.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Data.this,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                        }
                                    });
                        }
                    });
                }
                else{
                    Toast.makeText(Data.this, "Error", Toast.LENGTH_SHORT).show();
                }
                 }
             });
         }
     }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            if(data.getData()!=null){
                binding.ivupload.setImageURI(data.getData());
                SelectedImage = data.getData();
            }
        }
    }
}