package com.example.myapplication;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
            import androidx.fragment.app.DialogFragment;

            import android.app.DatePickerDialog;
            import android.content.Intent;
            import android.graphics.Color;
            import android.graphics.drawable.ColorDrawable;
            import android.os.Bundle;
            import android.text.TextUtils;
            import android.util.Log;
            import android.util.Patterns;
            import android.view.View;
            import android.widget.Button;
            import android.widget.DatePicker;
            import android.widget.EditText;
            import android.widget.TextView;
            import android.widget.Toast;

    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;
            import com.google.firebase.database.DatabaseReference;
            import com.google.firebase.database.FirebaseDatabase;

            import java.text.DateFormat;
            import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    Button btLogin;
    TextView callSignup;
    EditText etEmail, etPassword1;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btLogin = findViewById(R.id.bt_login);
        callSignup = findViewById(R.id.tv_callSignup);
        etEmail = findViewById(R.id.et_email);
        etPassword1 = findViewById(R.id.et_password1);

        fAuth = FirebaseAuth.getInstance();
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = etPassword1.getText().toString().trim();
                String email = etEmail.getText().toString().trim();


                if (email.isEmpty()) {
                    etEmail.setError("Username is required!");
                    etPassword1.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    etPassword1.setError("Password id required!");
                    etPassword1.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    etPassword1.setError("Minimum length of password should be 6");
                    etPassword1.requestFocus();
                    return;
                }

                // authenticate the user

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity2.this, "Successfully Login!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else {
                            Toast.makeText(MainActivity2.this, "ERROR!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        callSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });

    }
}

