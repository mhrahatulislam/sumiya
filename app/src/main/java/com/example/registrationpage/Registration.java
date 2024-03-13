package com.example.registrationpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    private EditText editText_name,editText_email,editText_password;
    private TextView textView_clicktologin;
    private Button button_sing_up;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textView_clicktologin=findViewById(R.id.textview_clicktologin);

        editText_name=findViewById(R.id.edittext_name_regi);
        editText_email=findViewById(R.id.edittext_email_regi);
        editText_password=findViewById(R.id.editTextTextPassword);
        button_sing_up=findViewById(R.id.btn_singup);

        mAuth=FirebaseAuth.getInstance();

        textView_clicktologin=findViewById(R.id.textview_clicktologin);

        textView_clicktologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });


        button_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = editText_email.getText().toString(); // Corrected here
                password = editText_password.getText().toString(); // Corrected here

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Registration.this, "Enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Registration.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "Account Ceated.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(Registration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}