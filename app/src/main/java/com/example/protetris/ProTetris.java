package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProTetris extends AppCompatActivity {

    private MainBoard mainBoard;
    private static ImageView rotateButton;
    private static ImageView leftButton;
    private static ImageView rightButton;
    private static TextView points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_tetris);

        //Get the ID of each Button to give it the functionality
        rotateButton = findViewById(R.id.rotateButton);
        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);
        points =  findViewById(R.id.textScore);

        //Start game
        mainBoard = findViewById(R.id.protetris);
        mainBoard.startTetris();
    }

    public static ImageView getLeftButton() {
        return leftButton;
    }

    public static ImageView getRightButton() {
        return rightButton;
    }

    public static ImageView getRotateButton() {
        return rotateButton;
    }

    public static TextView getPoints() {
        return points;
    }
}
