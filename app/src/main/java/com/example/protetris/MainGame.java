package com.example.protetris;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainGame extends View implements View.OnClickListener {

    private MainBoard mainBoard;
    private ProTetris proTetris;
    private ImageView rotateButton;
    private ImageView leftButton;
    private ImageView rightButton;
    private ImageView downButton;
    private TextView actualPoints;
    private Score score;
    private Timer timer;
    private int cont;
    private List<Piece> pieces;
    private UpcomingPiece upcomingPiece;
    private int timerPeriod;
    private boolean cond=false;
    int num;
    

    public MainGame(Context context, UpcomingPiece upcomingPiece, MainBoard mainBoard) {
        super(context);
        this.timer = new Timer();
        this.proTetris = (ProTetris) context;
        this.upcomingPiece = upcomingPiece;
        this.timerPeriod = 1000; //La pieza baja cada segundo
        this.cont = 0;
        this.score = new Score();
        this.mainBoard = mainBoard;
        this.pieces = mainBoard.getPieces();
        this.actualPoints = proTetris.getPoints();
        this.actualPoints.append("0");

        this.rotateButton = proTetris.getRotateButton();
        this.leftButton = proTetris.getLeftButton();
        this.rightButton = proTetris.getRightButton();
        this.downButton = proTetris.getDownButton();
        this.rotateButton.setOnClickListener(this);
        this.leftButton.setOnClickListener(this);
        this.rightButton.setOnClickListener(this);
        this.downButton.setOnClickListener(this);

        startGame();
    }

    public MainGame(Context context, int num) {
        super(context);
        this.num = num;
    }

    public void startGame() {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                proTetris.runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {

                        if (!proTetris.getStop()) {

                            if (!gameOver()) {

                                if (!mainBoard.moveOneDown(mainBoard.getActualPiece())) {
                                    int rowsRemoved = mainBoard.removeCompleteLines();


                                    Piece actualPiece = mainBoard.getActualPiece();
                                    pieces.remove(actualPiece);

                                    pieces.add(new Piece((int) (Math.random() * 7) + 1));

                                    upcomingPiece.invalidate();

                                    if (rowsRemoved > 0) { //Si se han borrado líneas, se aumenta el marcador +30 por cada una y se cambia el color
                                        cond=true;
                                        score.setActualScore(score.getActualScore() + rowsRemoved * 30);
                                        int points = score.getActualScore();

                                        actualPoints.setText(Integer.toString(points));
                                    }
                                }
                                if ((cont % 50 == 0) && cont != 0) {
                                    mainBoard.reduceBoard();
                                }
                                invalidate();
                                cont++;
                            }
                        }
                    }
                });
            }
        }, 0, timerPeriod);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int ancho = (int) (this.getResources().getDisplayMetrics().widthPixels * 0.068);
        int alto = (int) (this.getResources().getDisplayMetrics().heightPixels * 0.04375);
        if (cond) {
            num = (int) (Math.random() * 7 + 1);
            for (int row = 0; row < mainBoard.getActualRows(); row++) {
                for (int col = 0; col < mainBoard.getBOARD_NUM_COLS(); col++) {
                    int blocks = mainBoard.drawBlocks(row, col, num);
                    loadBlocks(blocks, paint, col, row, alto, ancho, canvas);
                }
            }

            cond = false;
        } else {
            for (int row = 0; row < mainBoard.getActualRows(); row++) {
                for (int col = 0; col < mainBoard.getBOARD_NUM_COLS(); col++) {
                    int blocks = mainBoard.drawBlocks(row, col, num);
                    loadBlocks(blocks, paint, col, row, alto, ancho, canvas);
                }
            }
        }
    }


    private void loadBlocks(int blocks, Paint paint, int col, int row, int alto, int ancho, Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(blocks);
        Rect rect = new Rect(col * ancho, row * alto, col * ancho + ancho, row * alto + alto);
        canvas.drawRect(rect, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void onClick(View view) {
        if (!proTetris.getStop()) {
            switch (view.getId()) {
                case R.id.rotateButton:
                    mainBoard.rotate(mainBoard.getActualPiece());
                    invalidate();
                    break;
                case R.id.leftButton:
                    mainBoard.moveToLeft(mainBoard.getActualPiece());
                    invalidate();
                    break;
                case R.id.rightButton:
                    mainBoard.moveToRight(mainBoard.getActualPiece());
                    invalidate();
                    break;
                case R.id.downButton:
                    mainBoard.moveDown(mainBoard.getActualPiece());
                    invalidate();
                    break;
            }
        }
    }

    public boolean gameOver() {
        if (this.mainBoard.checkGameOver(this.mainBoard.getActualPiece())) {
            Intent intent = new Intent(this.getContext(), GameOver.class);
            intent.putExtra("Score", score.getActualScore());
            this.timer.cancel();
            this.cont = 0;
            this.mainBoard.resetBoard(this.mainBoard.getBoard());
            proTetris.setStop(true);

            //Mostrar game over
            getContext().startActivity(intent);
            proTetris.finish();
            return true;
        }
        return false;
    }
}