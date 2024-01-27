package com.example.manageusers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    // Declaring globale variables
    public TextView tvLoginHere;
    public EditText etFirstname, etLastname, etEmail, etPassword;
    public Button btnRegister;
    // ProgressBar
    ProgressBar progressBar;

    // Firebase Auth
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeer);
        // Hide the status bar and make the ImageView appear at the top
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        // Firebase initialization
        mAuth = FirebaseAuth.getInstance();

        // initializing variables
        tvLoginHere = findViewById(R.id.tvAlreadyHaveAccount);
        etFirstname = findViewById(R.id.etFirstname);
        etLastname = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar);

        // if user is registered already, than go to login page
        tvLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // if user is not registered, then fill the data and register.
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String firstname, lastname, email, password;
                firstname = etFirstname.getText().toString();
                lastname = etLastname.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();

                // check if firstname, lastname, email or password are empty values.
                if(TextUtils.isEmpty(firstname)){
                    Toast.makeText(RegisterActivity.this, "Enter firstname!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(lastname)){
                    Toast.makeText(RegisterActivity.this, "Enter lastname!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Enter email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Register user in Firebase
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE); // make progressbar gon
                                if(task.isSuccessful()){
                                    // Sign in success
                                    Toast.makeText(RegisterActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    //if sign in fails
                                    Toast.makeText(RegisterActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}