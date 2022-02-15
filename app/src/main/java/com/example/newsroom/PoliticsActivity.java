package com.example.newsroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class PoliticsActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView Back, Title, Content;
    private Button Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_politics);


        Title = (TextView)findViewById(R.id.PoliticsArticleTitle1);
        Content = (TextView)findViewById(R.id.PoliticsArticleContent1);

        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");
        String str1 = intent.getStringExtra("message_key");

        // display the string into textView
        Title.setText(str);
        Content.setText(str1);

        Back = (TextView) findViewById(R.id.back);
        Back.setOnClickListener(this);
        Logout = (Button) findViewById(R.id.btnLogOut);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(PoliticsActivity.this, MainActivity.class));
            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                startActivity(new Intent(this, HomeActivity.class));
                break;
        }

    }
}

