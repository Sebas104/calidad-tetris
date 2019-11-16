package com.example.protetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

public class ProTetris extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    private ImageView rotateButton;
    private ImageView leftButton;
    private ImageView rightButton;
    private ImageView downButton;
    private TextView actualPoints;



    private TextView actualCombo;
    private boolean stop;

    private Button startButton;
    private MainBoard mainBoard;
    private MainGame game;
    private UpcomingPiece upcomingPiece;
    private MediaPlayer media;
    private int [] sounds ={R.raw.billie,R.raw.breakfree,R.raw.fuego,R.raw.gonnalive,R.raw.high,R.raw.guns,R.raw.eury,R.raw.carro};
    private int sound;
    private int colornum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_tetris);
        this.media=MediaPlayer.create(this,sounds[0]);
        this.media.setOnCompletionListener(this);
        this.mainBoard = new MainBoard();

        this.rotateButton = findViewById(R.id.rotateButton);
        this.leftButton = findViewById(R.id.leftButton);
        this.rightButton = findViewById(R.id.rightButton);
        this.downButton = findViewById(R.id.downButton);
        this.actualPoints =  findViewById(R.id.textScore);
        this.actualCombo=findViewById(R.id.textCombo);
        this.startButton = findViewById(R.id.start);
        this.stop = true;
        Bundle datos = this.getIntent().getExtras();
        this.colornum= datos.getInt("COLORKEY");

        RelativeLayout nextPiece = findViewById(R.id.pieceView);
        this.upcomingPiece = new UpcomingPiece(this, mainBoard,this.colornum, nextPiece);
        nextPiece.addView(upcomingPiece);

        this.game = new MainGame(this, this.upcomingPiece, this.mainBoard,this.colornum);
        RelativeLayout gameBoard = findViewById(R.id.tetrisBoard);
        gameBoard.addView(this.game);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startButton.getText().equals("Start") || startButton.getText().equals("Resume")) {
                    startButton.setText("Pause");
                    media.start();
                    stop = false;

                } else if (startButton.getText().equals("Pause")){
                    startButton.setText("Resume");
                    media.stop();
                    stop = true;
                    sound--;

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
    public TextView getActualCombo() {
        return actualCombo;
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
        media.stop();
        finish();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        this.sound++;
        if(this.sound == this.sounds.length){
            this.sound = 0;//empieza de nuevo
        }
        AssetFileDescriptor asset=this.getResources().openRawResourceFd(sounds[sound]);
        try{
            media.reset();
            media.setDataSource(asset.getFileDescriptor(), asset.getStartOffset(), asset.getDeclaredLength());
            media.prepare();
            media.start();
            asset.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}