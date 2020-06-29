package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class Signup extends AppCompatActivity {

    Button btSignUp;
    EditText etName, etEmail, etPhone, etPassword1, etUsername;
    FirebaseAuth fAuth;

    DatabaseReference dataUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dataUser = FirebaseDatabase.getInstance().getReference("user");

        btSignUp = findViewById(R.id.bt_signup);
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword1 = findViewById(R.id.et_password1);
        etUsername = findViewById(R.id.et_username);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addUser();

            }
        });


    }

    private void addUser() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString().trim();
        String password = etPassword1.getText().toString().trim();
        String username = etUsername.getText().toString().trim();


        if (name.isEmpty()){
            etName.setError("Name is required!");
            etPassword1.requestFocus();
            return;
        }

        if (phone.isEmpty()){
            etPhone.setError("Number Phone is required!");
            etPassword1.requestFocus();
            return;
        }

        if (email.isEmpty()){
            etEmail.setError("Email is required!");
            etPassword1.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please enter valid email");
            etEmail.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            etUsername.setError("Username is required!");
            etPassword1.requestFocus();
            return;
        }

        if (username.length()<6) {
            etUsername.setError("Min length: 6 characters");
            etPassword1.requestFocus();
            return;
        }

        if (username.length()>16) {
            etUsername.setError("Max length: 16 characters");
            etPassword1.requestFocus();
            return;
        }

        if (password.isEmpty()){
            etPassword1.setError("Password id required!");
            etPassword1.requestFocus();
            return;
        }

        if (password.length()<6){
            etPassword1.setError("Minimum length of password should be 6");
            etPassword1.requestFocus();
            return;
        }


        if (!TextUtils.isEmpty(name)) {

            String id =  dataUser.push().getKey();
            UserHelperClass user = new UserHelperClass(name, email, phone, username, password);
            dataUser.child(id).setValue(user);

            Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));



        } else {
            Toast.makeText(this, "You should enter a name", Toast.LENGTH_LONG).show();
        }
    }

}