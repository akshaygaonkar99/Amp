package com.example.manjunath.farmerhelpapp.others;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.manjunath.farmerhelpapp.R;
import com.example.manjunath.farmerhelpapp.activity.Moreinfo;
import com.example.manjunath.farmerhelpapp.activity.booking;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Labours extends AppCompatActivity implements ImageAdapter.OnItemClickListener{
    public static final String NAME = "miname";
    public static final String RATE = "mirate";
    public static final String DESP = "midesp";
    public static final String PINCODE = "mipinode";
    public static final String CATEGORY = "micategory";
    public static final String CONTACT = "micont";
    public static final String IMAGEURL = "miimageurl";
    public static final String DIST ="midist";
    public static final String EMAIL ="miemail";

    private RecyclerView mrecyclerview;
    private ProgressBar mprogressbar;
    private ImageAdapter mAdapter;
    private FirebaseDatabase mdatabase;
    private FirebaseStorage mstorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mdblistener;
    private ArrayList<Upload> muploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mrecyclerview = findViewById(R.id.recycleview);
        mrecyclerview.setHasFixedSize(true);
        LinearLayoutManager mlinearlayout = new LinearLayoutManager(this);
        mlinearlayout.setReverseLayout(true);
        mlinearlayout.setStackFromEnd(true);
        mrecyclerview.setLayoutManager(mlinearlayout);
        muploads = new ArrayList<>();
        mAdapter = new ImageAdapter(Labours.this,muploads);
        mAdapter.setOnItemClickListener(Labours.this);
        mrecyclerview.setAdapter(mAdapter);
        mprogressbar = findViewById(R.id.progress_circle);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads/Labour");
        mdatabase = FirebaseDatabase.getInstance();
        mstorage = FirebaseStorage.getInstance();
        mdblistener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                muploads.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Upload upload = postsnapshot.getValue(Upload.class);
                    upload.setkey(postsnapshot.getKey());
                    muploads.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mprogressbar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Labours.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                mprogressbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void ondeleteClick(int position) {
        Upload selecteditem = muploads.get(position);
        final String selectedkey = selecteditem.getkey();
        StorageReference imageref = mstorage.getReferenceFromUrl(selecteditem.getMimageurl());
        imageref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedkey).removeValue();
                Toast.makeText(Labours.this,"Item Deleted",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onmoreinfoClick(int position) {
        Intent intent = new Intent(this,Moreinfo.class);
        Upload clickeditem = muploads.get(position);
        intent.putExtra(NAME,clickeditem.getMname());
        intent.putExtra(RATE,clickeditem.getRentcost());
        intent.putExtra(CATEGORY,clickeditem.getCategory());
        intent.putExtra(PINCODE,clickeditem.getPincode());
        intent.putExtra(DESP,clickeditem.getDescription());
        startActivity(intent);
    }

    @Override
    public void onbooking(int position) {
        Intent intent1 = new Intent(Labours.this,booking.class);
        Upload clickeditem = muploads.get(position);
        intent1.putExtra(NAME,clickeditem.getMname());
        intent1.putExtra(RATE,clickeditem.getRentcost());
        intent1.putExtra(PINCODE,clickeditem.getPincode());
        intent1.putExtra(CONTACT,clickeditem.getContactno());
        intent1.putExtra(DIST,clickeditem.getDistrict());
        intent1.putExtra(IMAGEURL,clickeditem.getMimageurl());
        intent1.putExtra(EMAIL,clickeditem.getMemailId());
        intent1.putExtra(DESP,clickeditem.getDescription());
        startActivity(intent1);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mDatabaseRef.removeEventListener(mdblistener);
    }
}
