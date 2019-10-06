package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class GameOver extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        RelativeLayout gameOverScreen = findViewById(R.id.startAgain);

        gameOverScreen.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Cuando el usuario toque la pantalla
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Intent intent = new Intent(this, ProTetris.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }
}
