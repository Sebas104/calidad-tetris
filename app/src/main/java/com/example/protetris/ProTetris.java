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
        this.actualPoints =  findViewById(R.id.textScore);
        this.startButton = findViewById(R.id.start);
        this.stop = true;

        this.upcomingPiece = new UpcomingPiece(this, mainBoard);
        this.upcomingPiece.setLayoutParams(new RelativeLayout.LayoutParams(150,150));
        RelativeLayout relativeLayout = findViewById(R.id.pieceView);
        relativeLayout.addView(upcomingPiece);

        this.game = new MainGame(this, this.upcomingPiece, this.mainBoard);
        this.game.setLayoutParams(new RelativeLayout.LayoutParams(700, 1400));
        RelativeLayout gameBoard = findViewById(R.id.tetrisBoard);
        gameBoard.addView(this.game);
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

    public TextView getPoints() {
        return actualPoints;
    }

    public boolean getStop() {
        return this.stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }


    public void stopGame(View view) {
        if (startButton.getText().equals("Start")) {
            startButton.setText("Pause");
            stop = false;
        }
        if (startButton.getText().equals("Pause")) {
            startButton.setText("Start");
            stop = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        this.stop = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }
}
