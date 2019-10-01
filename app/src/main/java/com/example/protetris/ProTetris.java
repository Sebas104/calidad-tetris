package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProTetris extends AppCompatActivity {

    private MainBoard mainBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_tetris);
        mainBoard = findViewById(R.id.group);
    }
}
