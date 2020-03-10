package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Con setTheme invocamos lo que es el splash screen
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Boton de Play
    public void startGame(View view) {
        Intent intent = new Intent(this,MainColor.class);
        startActivity(intent);
    }

    //Boton de Exit
    public void exitGame(View view) {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    //Boton de Controls
    public void enterControls(View view) {
        Intent intent = new Intent(this, Controls.class);
        startActivity(intent);
    }

    public void ranking() {
        Intent intent = new Intent(this, GameOver.class);

        intent.putExtra("played",Boolean.FALSE);
        intent.putExtra("score", 0);
        intent.putExtra("COLORKEY", 0);

        startActivity(intent);


    }
}
