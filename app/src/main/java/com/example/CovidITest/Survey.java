package com.example.CovidITest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

public class Survey extends AppCompatActivity {

    Button submit, next1, next2, next3, next4;
    RadioButton T1, F1, T2, F2, F3, T3, F4, T4, F5, T5;
    LinearLayout q1, q2, q3, q4, q5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        submit = findViewById(R.id.submit);
        next1 = findViewById(R.id.next1);
        next2 = findViewById(R.id.next2);
        next3 = findViewById(R.id.next3);
        next4 = findViewById(R.id.next4);


        T1 = findViewById(R.id.True1);
        F1 = findViewById(R.id.False1);
        T2 = findViewById(R.id.True2);
        F2 = findViewById(R.id.False2);
        T3 = findViewById(R.id.True3);
        F3 = findViewById(R.id.False3);
        T4 = findViewById(R.id.True4);
        F4 = findViewById(R.id.False4);
        T5 = findViewById(R.id.True5);
        F5 = findViewById(R.id.False5);

        q1 = findViewById(R.id.Q1);
        q2 = findViewById(R.id.Q2);
        q3 = findViewById(R.id.Q3);
        q4 = findViewById(R.id.Q4);
        q5 = findViewById(R.id.Q5);

        submit.setVisibility(View.GONE);
        q2.setVisibility(View.GONE);
        q3.setVisibility(View.GONE);
        q4.setVisibility(View.GONE);
        q5.setVisibility(View.GONE);

        Intent intent=getIntent();


        int age, genre;
        age = intent.getIntExtra("age", 0);
        genre = intent.getIntExtra("gender", 0);


        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (T1.isChecked()||F1.isChecked()){
                    q2.setVisibility(View.VISIBLE);
                    q1.setVisibility(View.GONE);
                }else {
                    Toast.makeText(getApplicationContext(),"Repond au question s'il vous-plait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (T2.isChecked()||F2.isChecked()){
                    q3.setVisibility(View.VISIBLE);
                    q2.setVisibility(View.GONE);
                }else {
                    Toast.makeText(getApplicationContext(),"Repond au question s'il vous-plait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (T3.isChecked()||F3.isChecked()){
                    q4.setVisibility(View.VISIBLE);
                    q3.setVisibility(View.GONE);
                }else {
                    Toast.makeText(getApplicationContext(),"Repond au question s'il vous-plait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        next4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (T4.isChecked()||F4.isChecked()){
                    q5.setVisibility(View.VISIBLE);
                    q4.setVisibility(View.GONE);
                    submit.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(getApplicationContext(),"Repond au question s'il vous-plait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (T5.isChecked()||F5.isChecked()){
                    int r1=0, r2=0, r3=0, r4=0, r5=0;
                    if (T1.isChecked()){
                        r1 = 1;
                    }
                    if (T2.isChecked()){
                        r2 = 1;
                    }
                    if (T3.isChecked()){
                        r3 = 1;
                    }
                    if (T4.isChecked()){
                        r4 = 1;
                    }
                    if (T5.isChecked()){
                        r5 = 1;
                    }
                    final UserDatabaseHundler insertUser = new UserDatabaseHundler(getApplicationContext());
                    User user = new User(genre,age,r1,r2,r3,r4,r5);
                    insertUser.insertSurvey(user);

                    Intent intent = new Intent(getApplicationContext(), Statics.class);

                    int i = 0;
                    if (user.q1==1){
                        i++;
                    }
                    if (user.q2==1){
                        i++;
                    }
                    if (user.q3==1){
                        i++;
                    }
                    if (user.q4==1){
                        i++;
                    }
                    if (user.q5==1){
                        i++;
                    }
                    if (i>2){
                        intent.putExtra("res",1);
                    }
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Repond au question s'il vous-plait", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}