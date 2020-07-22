package com.tiaa.tiaaprepupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    //defining firebase auth object
    private FirebaseAuth firebaseAuth;

    private EditText email, password;
    private Button registerBtn;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        email = findViewById(R.id.email);
        password = findViewById(R.id.editTextPassword);
        registerBtn = findViewById(R.id.signUp);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        registerBtn.setOnClickListener((View.OnClickListener) this);

    }

    private void registerUser() {

        //getting email and password from edit texts
        String e = email.getText().toString().trim();
        String p = password.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(e)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(p)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();


        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(e, p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(RegisterActivity.this, Dashboard.class));

                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        //calling register method on click
        registerUser();
    }
}


