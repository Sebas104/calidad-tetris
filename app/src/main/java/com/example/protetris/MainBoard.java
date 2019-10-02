package com.example.protetris;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainBoard extends Block implements View.OnClickListener{

    //Constants and variables
    public static final int Z_PIECE = 0;
    public static final int S_PIECE = 1;
    public static final int O_PIECE = 2;
    public static final int I_PIECE = 3;
    public static final int J_PIECE = 4;
    public static final int L_PIECE = 5;
    public static final int T_PIECE = 6;

    private ImageView rotateButton;
    private ImageView leftButton;
    private ImageView rightButton;
    private TextView currentPoints;

    private int count;
    private long speedFall;
    private Piece actualPiece;

    public static Board actualBoard;
    public static Board notPieceBoard;
    public static Board lastBoard;

    private Refresh redraw = new Refresh();

    private static int [] randomArray = {-1, -1};
    private boolean moved;

    private  class Refresh extends Handler {
        @Override
        public void handleMessage(Message m) {
            if (m.what == 1){
                actualBoard.copyBoard(lastBoard);
                int LinesClear = actualBoard.checkLine();
                notPieceBoard.copyBoard(actualBoard);
                actualPiece = randomPiece(getRandom(), 4, 0);
                count ++;

                if (!actualBoard.insertPiece(actualPiece) ) {
                    //Hacer Game Over
                    //GameOver();
                    startTetris();
                }
                redraw.sleep(speedFall);

            }
            else{
                clearBlocks();
                updateBoard();
                actualBoard.resetBoard();
                actualBoard.copyBoard(notPieceBoard);
                tetrisMove();
                MainBoard.this.invalidate();
            }


        }

    public void sleep(long delay){
        this.removeMessages(0);
        sendEmptyMessageDelayed(0,delay);
    }
};


    //Constructors
    public MainBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        starMainBoard();
    }

    public MainBoard(Context context, AttributeSet attrs, int style) {
        super(context, attrs,style);
        starMainBoard();
    }

    private Piece randomPiece(int piece, int x, int y) {
        switch (piece) {
            case Z_PIECE:
                return new ZPiece(x ,y);
            case S_PIECE:
                return new SPiece(x, y);
            case O_PIECE:
                return new OPiece(x, y);
            case I_PIECE:
                return new IPiece(x, y);
            case J_PIECE:
                return  new JPiece(x, y);
            case L_PIECE:
                return new LPiece(x, y);
            case T_PIECE:
                return new TPiece(x, y);
            default:
                   return randomPiece(piece, 4, 0);
        }
    }

    private void starMainBoard() {
        setFocusable(true);
        actualBoard = new Board();
        notPieceBoard = new Board();
        lastBoard = new Board();

        currentPoints = ProTetris.getPoints();
        currentPoints.append("0");

        rotateButton = ProTetris.getRotateButton();
        leftButton = ProTetris.getLeftButton();
        rightButton = ProTetris.getRightButton();
        rotateButton.setOnClickListener(this);
        leftButton.setOnClickListener(this);
        rightButton.setOnClickListener(this);

        resetBlocks(NUM_BLOCKS + 10);
    }

    public void startTetris() {
        speedFall = 1200;
        lastBoard.resetBoard();
        actualBoard.resetBoard();
        notPieceBoard.resetBoard();
        count = 0;
        redraw.sendEmptyMessage(1);
    }

    public void updateTetris() {
        clearBlocks();
        updateBoard();
        MainBoard.this.invalidate();
    }

    private void updateBoard() {
        lastBoard.copyBoard(actualBoard);
        for(int y = 0; y < Board.NUM_ROW; y++){
            for(int x = 0; x < Board.NUM_COL; x++) {
                setBlock(lastBoard.getBoardValue(y, x), y, x);
            }
        }
    }

    public int getRandom() {
        if (randomArray[1] == -1) {
            randomArray[1] = (int) Math.floor(Math.random() * 7);
        }
        randomArray[0] = randomArray[1];
        randomArray[1] = (int)Math.floor(Math.random()*7);
        next = randomArray[1];
        return randomArray[0];
    }

    private void tetrisMove() {
        if(actualPiece.goDown(actualBoard)){
            actualBoard.insertPiece(actualPiece);
            redraw.sleep(speedFall);
        }
        else {
            redraw.sendEmptyMessageDelayed(1, 1000);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rotateButton:
                actualBoard.resetBoard();
                actualBoard.copyBoard(notPieceBoard);
                actualPiece.rotatePiece(actualBoard);
                actualBoard.insertPiece(actualPiece);
                updateTetris();
            case R.id.leftButton:
                actualBoard.resetBoard();
                actualBoard.copyBoard(notPieceBoard);
                if (actualPiece.goLeft(actualBoard) &&
                        !actualPiece.touchY(actualPiece.getnPosY() + 1, actualPiece.getnPosX(), actualPiece.nBoard, actualBoard)) {
                    if (redraw.hasMessages(1)) {
                        redraw.removeMessages(1);
                        redraw.sendEmptyMessageDelayed(0, 400);
                    }
                    actualBoard.insertPiece(actualPiece);
                }
                updateTetris();
                break;
            case R.id.rightButton:
                actualBoard.resetBoard();
                actualBoard.copyBoard(notPieceBoard);
                if (actualPiece.goRight(actualBoard) &&
                        !actualPiece.touchY(actualPiece.getnPosY() + 1, actualPiece.getnPosX(), actualPiece.nBoard, actualBoard)) {
                    if (redraw.hasMessages(1)) {
                        redraw.removeMessages(1);
                        redraw.sendEmptyMessageDelayed(0, 400);
                    }
                    actualBoard.insertPiece(actualPiece);
                }
                updateTetris();
                break;
        }
    }

}
