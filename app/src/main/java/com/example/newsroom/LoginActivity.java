package com.example.newsroom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView SignUp, Back, ForgotPassword;
    private Button Login;
    private EditText Email, Password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_login);

        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
        SignUp = (TextView) findViewById(R.id.signUp);
        SignUp.setOnClickListener(this);
        Back= (TextView) findViewById(R.id.back);
        Back.setOnClickListener(this);
        ForgotPassword= (TextView) findViewById(R.id.forgotPassword);
        ForgotPassword.setOnClickListener(this);
        Login= (Button) findViewById(R.id.btnLogin);
        Login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUp:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnLogin:
                loginuser();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, PasswordResetActivity.class));
                break;

        }
    }

    private void loginuser(){
        String email = Email.getText().toString().trim();
        String password =Password.getText().toString().trim();

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
        if (password.isEmpty()){
            Password.setError("Password is required");
            Password.requestFocus();
            return;
        }

        if (password.length() < 8){
            Password.setError("The Min password length should be 6 characters!");
            Password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() & email.equals("Admin@gmail.com")) {
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                } else if (task.isSuccessful() ) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Your Password/Email is incorrect, Please try again", Toast.LENGTH_SHORT).show();

                }

                }

        });

    }


}
