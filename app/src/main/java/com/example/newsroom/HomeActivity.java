package com.example.newsroom;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView Title1,Title2,Title3,Title4, Paragraph1, Paragraph2, Paragraph3, Paragraph4;
    private ImageView Icon1,Icon2, Icon3, Icon4;
    private Button Logout;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
            Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
            setContentView(R.layout.activity_home);

            Title1 = (TextView) findViewById(R.id.c1);
            Title1.setOnClickListener(this);
            Title2= (TextView) findViewById(R.id.c2);
            Title2.setOnClickListener(this);
            Title3 = (TextView) findViewById(R.id.c3);
            Title3.setOnClickListener(this);
            Title4 = (TextView) findViewById(R.id.c4);
            Title4.setOnClickListener(this);

            Paragraph1 = (TextView) findViewById(R.id.p1);
            Paragraph1.setOnClickListener(this);
            Paragraph2= (TextView) findViewById(R.id.p2);
            Paragraph2.setOnClickListener(this);
            Paragraph3 = (TextView) findViewById(R.id.p3);
            Paragraph3.setOnClickListener(this);
            Paragraph4 = (TextView) findViewById(R.id.p4);
            Paragraph4.setOnClickListener(this);


            Icon1 = (ImageView) findViewById(R.id.i1);
            Icon1.setOnClickListener(this);
            Icon2= (ImageView) findViewById(R.id.i2);
            Icon2.setOnClickListener(this);
            Icon3 = (ImageView) findViewById(R.id.i3);
            Icon3.setOnClickListener(this);
            Icon4 = (ImageView) findViewById(R.id.i4);
            Icon4.setOnClickListener(this);

            Logout = (Button) findViewById(R.id.btnLogOut);
            Logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                }
            });


        }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c1:
                startActivity(new Intent(this, PoliticsActivity.class));
                break;
            case R.id.c2:
                startActivity(new Intent(this, SportActivity.class));
                break;
            case R.id.c3:
                startActivity(new Intent(this, HealthActivity.class));
                break;
            case R.id.c4:
                startActivity(new Intent(this, TechnologyActivity.class));
                break;

            case R.id.p1:
                startActivity(new Intent(this, PoliticsActivity.class));
                break;
            case R.id.p2:
                startActivity(new Intent(this, SportActivity.class));
                break;
            case R.id.p3:
                startActivity(new Intent(this, HealthActivity.class));
                break;
            case R.id.p4:
                startActivity(new Intent(this, TechnologyActivity.class));
                break;

            case R.id.i1:
                startActivity(new Intent(this, PoliticsActivity.class));
                break;
            case R.id.i2:
                startActivity(new Intent(this, SportActivity.class));
                break;
            case R.id.i3:
                startActivity(new Intent(this, HealthActivity.class));
                break;
            case R.id.i4:
                startActivity(new Intent(this, TechnologyActivity.class));
                break;

        }
    }
}
