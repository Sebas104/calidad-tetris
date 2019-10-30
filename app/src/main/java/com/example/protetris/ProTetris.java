package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ProTetris extends AppCompatActivity {

    private ImageView rotateButton;
    private ImageView leftButton;
    private ImageView rightButton;
    private ImageView downButton;
    private TextView actualPoints;
    private boolean stop;
    private Button startButton;
    private MainBoard mainBoard;
    private MainGame game;
    private UpcomingPiece upcomingPiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_tetris);

        this.mainBoard = new MainBoard();
        //Get the ID of each Button to give it the functionality
        this.rotateButton = findViewById(R.id.rotateButton);
        this.leftButton = findViewById(R.id.leftButton);
        this.rightButton = findViewById(R.id.rightButton);
        this.downButton = findViewById(R.id.downButton);
        this.actualPoints =  findViewById(R.id.textScore);
        this.startButton = findViewById(R.id.start);
        this.stop = true;

        this.upcomingPiece = new UpcomingPiece(this, mainBoard);
        RelativeLayout nextPiece = findViewById(R.id.pieceView);
        nextPiece.addView(upcomingPiece);

        this.game = new MainGame(this, this.upcomingPiece, this.mainBoard);
        RelativeLayout gameBoard = findViewById(R.id.tetrisBoard);
        gameBoard.addView(this.game);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startButton.getText().equals("Start") || startButton.getText().equals("Resume")) {
                    startButton.setText("Pause");
                    stop = false;
                } else if (startButton.getText().equals("Pause")){
                    startButton.setText("Resume");
                    stop = true;
                }
            }
        });
    }


    public ImageView getLeftButton() {
        return leftButton;
    }

    public ImageView getRightButton() {
        return rightButton;
    }

    public ImageView getRotateButton() {
        return rotateButton;
    }

    public ImageView getDownButton() {
        return downButton;
    }

    public TextView getPoints() {
        return actualPoints;
    }

    public boolean getStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void onPause() {
        super.onPause();
        stop = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        stop = true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }
}
