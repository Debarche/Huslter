package com.example.newsroom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView SignIn, Back;
    private Button Register;
    private EditText Firstname, Surname, Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        SignIn = (TextView) findViewById(R.id.signIn);
        SignIn.setOnClickListener(this);
        Back= (TextView) findViewById(R.id.back);
        Back.setOnClickListener(this);

        Firstname = (EditText) findViewById(R.id.firstname);
        Surname = (EditText) findViewById(R.id.surname);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);

        Register = (Button) findViewById(R.id.register);
        Register.setOnClickListener(this);



    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register:
                registerUser();
                break;
        }
    }
    private void registerUser() {
        //GETTING ENTRIES FROM ALL THE EDITTEXT CONERTING THEM INTO STRINGS AND SAVING THEM INTO VARIABLES
        String firstname = Firstname.getText().toString().trim();
        String surname = Surname.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String password = (Password.getText().toString().trim());

        //CREATING VALIDATION FOR ALL THE FIELDS
        if (firstname.isEmpty()){
            Firstname.setError("UserName is required");
            Firstname.requestFocus();
            return;
        }
        if (surname.isEmpty()){
            Surname.setError("FirstName is required");
            Surname.requestFocus();
            return;
        }

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

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            UserClass user = new UserClass(firstname,surname,email, password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()
                                    {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(RegisterActivity.this, "Registration Completed Successfully", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                            }else{
                                                Toast.makeText(RegisterActivity.this, "Registration failed, Try again", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                    }
                });


    }

    //CREATING A METHOD TO ENCRYPT THE PASSWORD
   /* public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }*/
}
