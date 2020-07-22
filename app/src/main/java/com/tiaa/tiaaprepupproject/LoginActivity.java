package com.tiaa.tiaaprepupproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backBtn;
    Button loginBtn;
    private FirebaseAuth firebaseAuth;
    private EditText email, password;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //it will take us back to the main activity
            }
        });

        //Login
        loginBtn = findViewById(R.id.loginBtn2);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        progressDialog = new ProgressDialog(this);
        loginBtn.setOnClickListener((View.OnClickListener) this);
    }

    private void loginUser() {

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

        progressDialog.setMessage("Logging in...");
        progressDialog.show();


        //creating a new user
        firebaseAuth.signInWithEmailAndPassword(e, p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //checking if success
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, Dashboard.class));
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View v) {
        //calling register method on click
        loginUser();
    }

}


