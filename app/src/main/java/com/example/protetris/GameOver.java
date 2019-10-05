package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class GameOver extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        @SuppressLint("ResourceType") ConstraintLayout gameOverScreen = findViewById(R.layout.activity_game_over);

        gameOverScreen.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Cuando el usuario toque la pantalla
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Intent intent = new Intent(this, ProTetris.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
