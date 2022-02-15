package com.example.newsroom;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class AdminActivity extends AppCompatActivity {
    private Button Logout, PoliticsDelete, PoliticsPost, SportsDelete, SportPost, HealthDelete, HealthPost, TechnologyDelete,TechnologyPost;
    private EditText PoliticsTitle, PoliticsContent, SportsTitle, SportContent, HealthTitle, HealthContent, TechnologyTitle,TechnologyContent;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    PoliticesArticle politicesArticle;
    HealthArticles healthArticles;
    sportArticles SportArticles;
    technologyArticles TechnologyArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_admin);

        firebaseDatabase = FirebaseDatabase.getInstance();



        PoliticsTitle = (EditText) findViewById(R.id.ArticleTitle1);
        PoliticsContent = (EditText) findViewById(R.id.ArticleContent1);
        politicesArticle = new PoliticesArticle();

        SportsTitle = (EditText) findViewById(R.id.ArticleTitle2);
        SportContent = (EditText) findViewById(R.id.ArticleContent2);
        SportArticles = new sportArticles();

        HealthTitle = (EditText) findViewById(R.id.ArticleTitle3);
        HealthContent = (EditText) findViewById(R.id.ArticleContent3);
        healthArticles = new HealthArticles();

        TechnologyTitle = (EditText) findViewById(R.id.ArticleTitle4);
        TechnologyContent = (EditText) findViewById(R.id.ArticleContent4);
        TechnologyArticles = new technologyArticles();

        PoliticsDelete = (Button) findViewById(R.id.btnDelete1);
        PoliticsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        PoliticsPost = (Button) findViewById(R.id.btnPost1);
        PoliticsPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = firebaseDatabase.getReference("PoloticesArticle");

                String ArticleTitle = PoliticsTitle.getText().toString();
                String ArticleContent = PoliticsContent.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(ArticleTitle) && TextUtils.isEmpty(ArticleContent)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(AdminActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebasePolitics(ArticleTitle, ArticleContent);


                }
            }

        });



        SportsDelete = (Button) findViewById(R.id.btnDelete2);
        SportsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        SportPost = (Button) findViewById(R.id.btnPost2);
        SportPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = firebaseDatabase.getReference("SportArticle");

                String ArticleTitle = SportsTitle.getText().toString();
                String ArticleContent = SportContent.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(ArticleTitle) && TextUtils.isEmpty(ArticleContent)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(AdminActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebaseSport(ArticleTitle, ArticleContent);


                }

            }
        });


        HealthDelete = (Button) findViewById(R.id.btnDelete3);
        HealthDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        HealthPost = (Button) findViewById(R.id.btnPost3);
        HealthPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = firebaseDatabase.getReference("HealthArticle");

                String ArticleTitle = HealthTitle.getText().toString();
                String ArticleContent = HealthContent.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(ArticleTitle) && TextUtils.isEmpty(ArticleContent)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(AdminActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebaseHealth(ArticleTitle, ArticleContent);


                }

            }
        });

        TechnologyDelete = (Button) findViewById(R.id.btnDelete4);
        TechnologyDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        TechnologyPost = (Button) findViewById(R.id.btnPost4);
        TechnologyPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference = firebaseDatabase.getReference("SportArticle");

                String ArticleTitle = TechnologyTitle.getText().toString();
                String ArticleContent = TechnologyContent.getText().toString();

                // below line is for checking weather the
                // edittext fields are empty or not.
                if (TextUtils.isEmpty(ArticleTitle) && TextUtils.isEmpty(ArticleContent)) {
                    // if the text fields are empty
                    // then show the below message.
                    Toast.makeText(AdminActivity.this, "Please add some data.", Toast.LENGTH_SHORT).show();
                } else {
                    // else call the method to add
                    // data to our database.
                    addDatatoFirebaseTechnology(ArticleTitle, ArticleContent);


                }

            }
        });



        Logout = (Button) findViewById(R.id.btnLogOut);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(AdminActivity.this, MainActivity.class));
            }
        });



}
    //ADDING THE ARTICLE TO THHE DATABASE (FIREBASE)
    private void addDatatoFirebaseSport(String ArticleTitle, String ArticleContent) {
        // below 3 lines of code is used to set
        // data in our object class.
        SportArticles.setSportsTitle(ArticleTitle);
        SportArticles.setSportContent(ArticleContent);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(SportArticles);

                // after adding this data we are showing toast message.
                Toast.makeText(AdminActivity.this, "data added", Toast.LENGTH_SHORT).show();
                // get the value which input by user in EditText
                // and convert it to string
                String str = ArticleTitle.toString();
                String str1 = ArticleContent.toString();

                // Create the Intent object of this class Context() to Second_activity class
                Intent intent = new Intent(getApplicationContext(), SportActivity.class);

                // now by putExtra method put the value in key, value pair
                // key is message_key by this key we will receive the value, and put the string

                intent.putExtra("message_key", str);
                //intent.putExtra("message_key", str1);
                Toast.makeText(AdminActivity.this, "Article added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(AdminActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addDatatoFirebaseHealth(String ArticleTitle, String ArticleContent) {
        // below 3 lines of code is used to set
        // data in our object class.
        healthArticles.setHealthTitle(ArticleTitle);
        healthArticles.setHealthContent(ArticleContent);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(healthArticles);

                // after adding this data we are showing toast message.
                Toast.makeText(AdminActivity.this, "data added", Toast.LENGTH_SHORT).show();
                // get the value which input by user in EditText
                // and convert it to string
                String str = ArticleTitle.toString();
                String str1 = ArticleContent.toString();

                // Create the Intent object of this class Context() to Second_activity class
                Intent intent = new Intent(getApplicationContext(), HealthActivity.class);

                // now by putExtra method put the value in key, value pair
                // key is message_key by this key we will receive the value, and put the string

                intent.putExtra("message_key", str);
                //intent.putExtra("message_key", str1);
                Toast.makeText(AdminActivity.this, "Article added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(AdminActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addDatatoFirebaseTechnology(String ArticleTitle, String ArticleContent) {
        // below 3 lines of code is used to set
        // data in our object class.
        TechnologyArticles.setTechnology_Title(ArticleTitle);
        TechnologyArticles.setTechnology_Content(ArticleContent);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(TechnologyArticles);

                // after adding this data we are showing toast message.
                Toast.makeText(AdminActivity.this, "data added", Toast.LENGTH_SHORT).show();
                // get the value which input by user in EditText
                // and convert it to string
                String str = ArticleTitle.toString();
                String str1 = ArticleContent.toString();

                // Create the Intent object of this class Context() to Second_activity class
                Intent intent = new Intent(getApplicationContext(), TechnologyActivity.class);

                // now by putExtra method put the value in key, value pair
                // key is message_key by this key we will receive the value, and put the string

                intent.putExtra("message_key", str);
                //intent.putExtra("message_key", str1);
                Toast.makeText(AdminActivity.this, "Article added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(AdminActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void addDatatoFirebasePolitics(String ArticleTitle, String ArticleContent) {
        // below 3 lines of code is used to set
        // data in our object class.
        politicesArticle.setPoliticsTitle(ArticleTitle);
        politicesArticle.setPoliticsContent(ArticleContent);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(politicesArticle);

                // after adding this data we are showing toast message.
                Toast.makeText(AdminActivity.this, "data added", Toast.LENGTH_SHORT).show();
                // get the value which input by user in EditText
                // and convert it to string
                String str = ArticleTitle.toString();
                String str1 = ArticleContent.toString();

                // Create the Intent object of this class Context() to Second_activity class
                Intent intent = new Intent(getApplicationContext(), PoliticsActivity.class);

                // now by putExtra method put the value in key, value pair
                // key is message_key by this key we will receive the value, and put the string

                intent.putExtra("message_key", str);
                //intent.putExtra("message_key", str1);
                Toast.makeText(AdminActivity.this, "Article added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(AdminActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
