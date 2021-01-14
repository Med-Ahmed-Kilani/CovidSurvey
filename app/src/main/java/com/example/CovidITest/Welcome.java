package com.example.CovidITest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    Button submit;
    RadioButton m, f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.SubmitBTN);
        m = findViewById(R.id.male);
        f = findViewById(R.id.female);

        Spinner sp = (Spinner)findViewById(R.id.SPAge);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TempAge;
                int age, gender;
                TempAge = sp.getSelectedItem().toString();


                if (TempAge.equals("--15")){
                    age = 0;
                }else if (TempAge.equals("15-49")){
                    age = 1;
                }else if (TempAge.equals("50-64")){
                    age = 2;
                }else {
                    age = 3;
                }

                if (m.isChecked()){
                    gender = 0;
                }else {
                    gender = 1;
                }

                Intent intent = new Intent(getApplicationContext(), Survey.class);
                intent.putExtra("age", age);
                intent.putExtra("gender", gender);
                startActivity(intent);
            }
        });


    }
}