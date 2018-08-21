package com.example.manjunath.farmerhelpapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import javax.mail.PasswordAuthentication;
import android.os.Bundle;
import java.util.Properties;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.manjunath.farmerhelpapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.manjunath.farmerhelpapp.others.Fertilizer.CONTACT;
import static com.example.manjunath.farmerhelpapp.others.Fertilizer.DIST;
import static com.example.manjunath.farmerhelpapp.others.Fertilizer.EMAIL;
import static com.example.manjunath.farmerhelpapp.others.Fertilizer.NAME;
import static com.example.manjunath.farmerhelpapp.others.Fertilizer.PINCODE;
import static com.example.manjunath.farmerhelpapp.others.Fertilizer.RATE;
import static com.example.manjunath.farmerhelpapp.others.Machinery.DESP;

public class booking extends AppCompatActivity {
    EditText name,contact,district,pincode;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        confirm = findViewById(R.id.bookb);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = findViewById(R.id.nameb);
                contact = findViewById(R.id.contactb);
                district = findViewById(R.id.districtb);
                pincode = findViewById(R.id.pincodeb);
                if (name.getText().toString() != null && contact.getText().toString() != null && district.getText().toString() != null && pincode.getText().toString() != null)
                {final String nameb = name.getText().toString();
                final String contb = contact.getText().toString();
                final String distb = district.getText().toString();
                final String pincodeb = pincode.getText().toString();
                final Intent intent1 = getIntent();
                String email = intent1.getStringExtra(EMAIL);
                String disp = intent1.getStringExtra(DESP);
                String contact1 = intent1.getStringExtra(CONTACT);
                String Rate = intent1.getStringExtra(RATE);
                String name = intent1.getStringExtra(NAME);
                String dist1 = intent1.getStringExtra(DIST);
                String pincode = intent1.getStringExtra(PINCODE);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String messageb = "Order taken by\n" + "name : " + nameb + "\n" + "contact no : " + contb + "\n" + "district : " + distb + "\n" + "pincode : " + pincodeb;
                String subject = "Your material is been ordered ";
                SendMail sm = new SendMail(booking.this, email, subject, messageb);
                sm.execute();
                String email1 = user.getEmail();
                String subjectb = "Your buying order is placed successfully";
                String message = "Brought item owner details : \n" + "name: " + name + "\n" + "email : " + email + "\n" + "contact no : " + contact1 + "\n" + "district : " +
                        dist1 + "\n" + "rentcost : " + Rate+"\n"+"description : "+disp+"\n please contact the uploader to receive the material/goods";
                SendMail sm1 = new SendMail(booking.this, email1, subjectb, message);
                sm1.execute();
                Toast.makeText(booking.this, "booking successfull", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(booking.this, Social.class);
                startActivity(intent);
            }
            else{
                    Toast.makeText(booking.this, "Empty fields present", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}