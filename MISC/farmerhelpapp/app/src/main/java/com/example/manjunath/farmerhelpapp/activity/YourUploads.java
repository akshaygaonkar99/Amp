package com.example.manjunath.farmerhelpapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.manjunath.farmerhelpapp.R;
import com.example.manjunath.farmerhelpapp.others.Fertilizer;
import com.example.manjunath.farmerhelpapp.others.ImageAdapter;
import com.example.manjunath.farmerhelpapp.others.ImageAdapter1;
import com.example.manjunath.farmerhelpapp.others.Upload;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YourUploads extends AppCompatActivity implements ImageAdapter1.OnItemClickListener {

    public static final String NAME = "miname";
    public static final String RATE = "mirate";
    public static final String DESP = "midesp";
    public static final String PINCODE = "mipinode";
    public static final String CATEGORY = "micategory";
    private RecyclerView mrecyclerview;
    private ProgressBar mprogressbar;
    private FirebaseStorage mstorage;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mDatabaseRef;
    private ImageAdapter1 mAdapter;
    private ArrayList<Upload> muploads;

    //private Button bookinginfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_uploads);
        mrecyclerview = findViewById(R.id.urecycleview);
        //bookinginfo = findViewById(R.id.booking);
        //bookinginfo.setText("Delete");
        mrecyclerview.setHasFixedSize(true);

        LinearLayoutManager mlinearlayout = new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(mlinearlayout);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads/Machinery");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();
        muploads = new ArrayList<>();
        mprogressbar = findViewById(R.id.uprogress_circle);
        Query query = FirebaseDatabase.getInstance().getReference("uploads/Machinery")
                .orderByChild("memailId")
                .equalTo(userEmail);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    muploads.add(upload);
                }
                mAdapter = new ImageAdapter1(YourUploads.this,muploads);
                mrecyclerview.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(YourUploads.this);
                mprogressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(YourUploads.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mprogressbar.setVisibility(View.INVISIBLE);
            }
        });

        Query query1 = FirebaseDatabase.getInstance().getReference("uploads/Fertilizer")
                .orderByChild("memailId")
                .equalTo(userEmail);

        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    muploads.add(upload);
                }
                mAdapter = new ImageAdapter1(YourUploads.this,muploads);
                mrecyclerview.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(YourUploads.this);
                mprogressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(YourUploads.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mprogressbar.setVisibility(View.INVISIBLE);
            }
        });

        Query query2 = FirebaseDatabase.getInstance().getReference("uploads/Labour")
                .orderByChild("memailId")
                .equalTo(userEmail);

        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    muploads.add(upload);
                }
                mAdapter = new ImageAdapter1(YourUploads.this,muploads);
                mrecyclerview.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(YourUploads.this);
                mprogressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(YourUploads.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mprogressbar.setVisibility(View.INVISIBLE);
            }
        });

        Query query3 = FirebaseDatabase.getInstance().getReference("uploads/Saplings")
                .orderByChild("memailId")
                .equalTo(userEmail);

        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    muploads.add(upload);
                }
                mAdapter = new ImageAdapter1(YourUploads.this,muploads);
                mrecyclerview.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(YourUploads.this);
                mprogressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(YourUploads.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mprogressbar.setVisibility(View.INVISIBLE);
            }
        });
        Query query4 = FirebaseDatabase.getInstance().getReference("uploads/Empty Fields")
                .orderByChild("memailId")
                .equalTo(userEmail);

        query4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    muploads.add(upload);
                }
                mAdapter = new ImageAdapter1(YourUploads.this,muploads);
                mrecyclerview.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(YourUploads.this);
                mprogressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(YourUploads.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mprogressbar.setVisibility(View.INVISIBLE);
            }
        });

        Query query5 = FirebaseDatabase.getInstance().getReference("uploads/Seeds")
                .orderByChild("memailId")
                .equalTo(userEmail);

        query5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    muploads.add(upload);
                }
                mAdapter = new ImageAdapter1(YourUploads.this,muploads);
                mrecyclerview.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(YourUploads.this);
                mprogressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(YourUploads.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mprogressbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onmoreinfoClick(int position) {
        finish();
        Intent intent = new Intent(YourUploads.this,YourUploads.class);
        startActivity(intent);
        Toast.makeText(YourUploads.this,"Item Deleted",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void oninfoClick(int position) {
        Intent intent = new Intent(this,Moreinfo.class);
        Upload clickeditem = muploads.get(position);
        intent.putExtra(NAME,clickeditem.getMname());
        intent.putExtra(RATE,clickeditem.getRentcost());
        intent.putExtra(CATEGORY,clickeditem.getCategory());
        intent.putExtra(PINCODE,clickeditem.getPincode());
        intent.putExtra(DESP,clickeditem.getDescription());
        startActivity(intent);
    }
}