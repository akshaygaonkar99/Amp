package com.example.manjunath.farmerhelpapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manjunath.farmerhelpapp.R;
import com.example.manjunath.farmerhelpapp.others.ImageAdapter;
import com.example.manjunath.farmerhelpapp.others.Machinery;
import com.example.manjunath.farmerhelpapp.others.Upload;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import static com.example.manjunath.farmerhelpapp.others.Machinery.CATEGORY;
import static com.example.manjunath.farmerhelpapp.others.Machinery.DESP;
import static com.example.manjunath.farmerhelpapp.others.Machinery.NAME;
import static com.example.manjunath.farmerhelpapp.others.Machinery.PINCODE;
import static com.example.manjunath.farmerhelpapp.others.Machinery.RATE;

public class Moreinfo extends AppCompatActivity  {
    public TextView textname,textpincode,textrate,textdescription,textcategory;
    private FirebaseStorage mstorage;
    private DatabaseReference mDatabaseRef;
    private List<Upload> muploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfo);
        Intent intent = getIntent();
        textname = findViewById(R.id.miname);
        textrate = findViewById(R.id.mirate);
        textcategory = findViewById(R.id.micategory);
        textpincode = findViewById(R.id.mipincode);
        textdescription = findViewById(R.id.midescription);
        textname.setText(intent.getStringExtra(NAME));
        textrate.setText(intent.getStringExtra(RATE));
        textdescription.setText(intent.getStringExtra(DESP));
        textpincode.setText(intent.getStringExtra(PINCODE));
        textcategory.setText(intent.getStringExtra(CATEGORY));
    }
}
