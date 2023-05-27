package com.example.randomnumsdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.randomnum.RandomNumberSDK;

public class MainActivity extends AppCompatActivity {
    TextView tvTextRandom;
    Button btnGetNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTextRandom = findViewById(R.id.tvTextRandom);
        btnGetNumber = findViewById(R.id.btnGetNumber);
        RandomNumberSDK randomNumberSDK = new RandomNumberSDK();
        btnGetNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomNumberSDK.getRandomNumber(new RandomNumberSDK.RandomNumberListener() {
                    @Override
                    public void onRandomNumberGenerated(int randomNumber) {
                        // Use the generated random number
                        tvTextRandom.setText(""+randomNumber);
                        //Toast.makeText(MainActivity.this, "Random Number: " + randomNumber, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRandomNumberError(String errorMessage) {
                        // Handle error
                     //   Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}