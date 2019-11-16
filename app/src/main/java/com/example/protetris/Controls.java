package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Controls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);
    }
    public void startGame(View view) {
        Intent intent = new Intent(this,MainColor.class);
        startActivity(intent);
        this.finish();
    }
}
