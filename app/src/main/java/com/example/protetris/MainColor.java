package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainColor extends AppCompatActivity {
    private MainGame main;
    private Button redButton;
    private Button blueButton;
    private Button yellowButton;
    private Button greenButton;
    private Button purpleButton;
    private Button blue2Button;
    private Button orangeButton;
    private Button multicolorButton;
    private int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_color);


        redButton = findViewById(R.id.red_button);
        blueButton = findViewById(R.id.blue_button);
        blue2Button = findViewById(R.id.blue2_button);
        greenButton = findViewById(R.id.green_button);
        yellowButton = findViewById(R.id.yellow_button);
        orangeButton = findViewById(R.id.orange_button);
        purpleButton = findViewById(R.id.purple_button);
        multicolorButton = findViewById(R.id.multicolor_button);



        greenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainColor.this, ProTetris.class);
                myIntent.putExtra("COLORKEY", 7); //Optional parameters
                Toast toast =Toast.makeText(getApplicationContext(),"Green color selected!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,25);
                toast.show();
                startActivity(myIntent);
            }

        });
        blueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainColor.this, ProTetris.class);
                myIntent.putExtra("COLORKEY", 1); //Optional parameters
                Toast toast =Toast.makeText(getApplicationContext(),"Marine blue color selected!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,25);
                toast.show();
                startActivity(myIntent);
            }

        });
        blue2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainColor.this, ProTetris.class);
                myIntent.putExtra("COLORKEY", 3); //Optional parameters
                Toast toast =Toast.makeText(getApplicationContext(),"Light blue color selected!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,25);
                toast.show();
                startActivity(myIntent);
            }

        });
        redButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainColor.this, ProTetris.class);
                myIntent.putExtra("COLORKEY", 2); //Optional parameters
                Toast toast =Toast.makeText(getApplicationContext(),"Red color selected!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,25);
                toast.show();
                startActivity(myIntent);
            }

        });
        purpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainColor.this, ProTetris.class);
                myIntent.putExtra("COLORKEY", 4); //Optional parameters
                startActivity(myIntent);
                Toast toast =Toast.makeText(getApplicationContext(),"Purple color selected!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,25);
                toast.show();
            }

        });
        yellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainColor.this, ProTetris.class);
                myIntent.putExtra("COLORKEY", 5); //Optional parameters
                startActivity(myIntent);
                Toast toast =Toast.makeText(getApplicationContext(),"Yellow color selected!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,25);
                toast.show();
            }

        });
        multicolorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainColor.this, ProTetris.class);
                myIntent.putExtra("COLORKEY", 0); //Optional
                Toast toast =Toast.makeText(getApplicationContext(),"Rainbow color selected!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,25);
                toast.show();
                startActivity(myIntent);
            }

        });
        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainColor.this, ProTetris.class);
                myIntent.putExtra("COLORKEY", 6); //Optional parameters
                Toast toast =Toast.makeText(getApplicationContext(),"Orange color selected!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,25);
                toast.show();
                startActivity(myIntent);
            }
        });



    }

    public int getN() {
        return n;
    }
}