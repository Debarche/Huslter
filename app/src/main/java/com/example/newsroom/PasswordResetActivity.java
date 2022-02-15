package com.example.newsroom;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class PasswordResetActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText Email;
    private Button ResetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_passwordreset);

        Email = (EditText) findViewById(R.id.email);
        auth = FirebaseAuth.getInstance();
        ResetPass =(Button) findViewById(R.id.btnReset);
        ResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();

            }
        });

    }
    public void resetPassword(){
        String email = Email.getText().toString().trim();
        if (email.isEmpty()){
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please provide a valid Email");
            Email.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PasswordResetActivity.this, "Check your Emails to reset password", Toast.LENGTH_SHORT).show();
                }  else{
                    Toast.makeText(PasswordResetActivity.this, "Something went wrong, try again", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
