package com.example.manjunath.farmerhelpapp.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manjunath.farmerhelpapp.R;
import com.example.manjunath.farmerhelpapp.others.Machinery;
import com.example.manjunath.farmerhelpapp.others.Upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class add_item extends AppCompatActivity {

    private EditText inputname, inputcontactno,inputdistrict,inputdescription,inputpincode,inputrate;
    private ImageButton addimage;
    private Spinner spinner,spinner1;
    private Button camera,gallery;
    private Button upload;
    private ImageView mimageview;
    private Uri mimageuri;

    private DatabaseReference mdatabaseref;
    private StorageReference mstorageref;

    private StorageTask muploadtask;

    private static final int PICK_IMAGE_REQUEST=1;
    private static final int PICK_IMAGE_REQUEST1=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        inputrate = (EditText)findViewById(R.id.rate);
        inputname = (EditText) findViewById(R.id.name);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String username = user.getDisplayName();
        inputname.setText(username);
        inputdescription = (EditText)findViewById(R.id.Description);
        inputcontactno = (EditText) findViewById(R.id.contactno);
        inputdistrict = (EditText) findViewById(R.id.district);
        inputpincode = (EditText) findViewById(R.id.pincode);
        addimage = (ImageButton) findViewById(R.id.addimage);
        gallery = (Button)findViewById(R.id.gallery);
        upload = (Button)findViewById(R.id.submit);
        mimageview = (ImageView)findViewById(R.id.image_view);
        spinner = (Spinner)findViewById(R.id.spinner1);
        spinner1 = (Spinner)findViewById(R.id.Category);
        int i=0;
        ArrayAdapter<CharSequence> myadapter = ArrayAdapter.createFromResource(this,R.array.Spinneritems
                ,android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> myadapter1 = ArrayAdapter.createFromResource(this,R.array.Spinneritems1
                ,android.R.layout.simple_spinner_item);

        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myadapter);
        spinner1.setAdapter(myadapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                mstorageref = FirebaseStorage.getInstance().getReference("uploads/"+selectedItemText);
                mdatabaseref = FirebaseDatabase.getInstance().getReference("uploads/"+selectedItemText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(add_item.this);
                View mView = getLayoutInflater().inflate(R.layout.add_image_dialog, null);

                final Button gallery = (Button) mView.findViewById(R.id.gallery);

                upload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(muploadtask!=null && muploadtask.isInProgress()){
                            Toast.makeText(add_item.this,"Upload in progress",Toast.LENGTH_SHORT).show();
                        }else {
                            uploadfile();
                            OpenImageActivity();
                        }

                    }
                });
                gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openfilechooser();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }



    private void OpenImageActivity() {
        String intentname = spinner1.getSelectedItem().toString();
        Intent intent = new Intent(this,YourUploads.class);
        startActivity(intent);
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr =getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadfile() {
        if(mimageuri!=null && inputname!=null && inputcontactno!=null && inputdistrict!=null && inputpincode!=null){
            StorageReference fileReference = mstorageref.child(System.currentTimeMillis()+"."+getFileExtension(mimageuri));
            muploadtask=fileReference.putFile(mimageuri)
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userEmail = user.getEmail();
                    String cost = inputrate.getText().toString()+" "+spinner.getSelectedItem().toString();
                    String uploadId = mdatabaseref.push().getKey();
                    Upload upload = new Upload(uploadId.toString(),
                            inputname.getText().toString(),
                            inputcontactno.getText().toString(),
                            inputdistrict.getText().toString(),
                            inputpincode.getText().toString(),
                            cost.toString(),
                            spinner1.getSelectedItem().toString(),
                            taskSnapshot.getDownloadUrl().toString(),
                            userEmail.toString(),
                            inputdescription.getText().toString());
                    mdatabaseref.child(uploadId).setValue(upload);
                    Toast.makeText(add_item.this,"Upload Successful",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
               Toast.makeText(add_item.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(this, "Blank fields present", Toast.LENGTH_SHORT).show();
        }
    }

    private void openfilechooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            mimageuri = data.getData();
            //Picasso.with(this).load(mimageuri).into(mimageview);
            mimageview.setImageURI(mimageuri);
        }
    }
}
