package com.example.CovidITest;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Statics extends AppCompatActivity {

    Button show;
    TextView tv, result, resultGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statics);

        show = findViewById(R.id.show);
        tv = findViewById(R.id.title);
        result = findViewById(R.id.resultat);
        resultGreen = findViewById(R.id.resultGreen);

        Intent intent = getIntent();
        if (intent.getIntExtra("res",0)==1){
            result.setVisibility(View.VISIBLE);
        }else {
            resultGreen.setVisibility(View.VISIBLE);
        }

        final int[] i = {0};
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final UserDatabaseHundler maDB = new UserDatabaseHundler(getApplicationContext());
                tv.setText(maDB.statics());
                show.setText("Retour");
                if (i[0] >0){
                    Intent intent = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(intent);
                }
                i[0]++;
            }
        });

    }
}