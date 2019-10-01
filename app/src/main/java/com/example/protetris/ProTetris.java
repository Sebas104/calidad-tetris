package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProTetris extends AppCompatActivity {

    private MainBoard mainBoard;
    private Button rotateButton;
    private Button leftButton;
    private Button rightButton;
    private TextView points;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_tetris);
        mainBoard = findViewById(R.id.protetris);

        //Get the ID of each Button to give it the functionality
        rotateButton = (Button) findViewById(R.id.rotateButton);
        leftButton = (Button) findViewById(R.id.leftButton);
        rightButton = (Button) findViewById(R.id.rightButton);
        points = (TextView) findViewById(R.id.textScore);
    }
}
