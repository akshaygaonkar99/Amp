package com.example.manjunath.farmerhelpapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.manjunath.farmerhelpapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    TextView txtslogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();
        if(userEmail != null){
            Intent intent = new Intent(MainActivity.this,Social.class);
            startActivity(intent);
        }
*/
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        txtslogan = (TextView)findViewById(R.id.txtslogin);


        //Typeface face = Typeface.createFromAsset(getAssets(),"fonts/NABILA.rar");
        //txtslogan.setTypeface(face);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginactivity = new Intent(MainActivity.this, loginactivity1.class);
                startActivity(loginactivity);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupactivity1 = new Intent(MainActivity.this, googlesignup.class);
                startActivity(signupactivity1);
            }
        });
    }
}
