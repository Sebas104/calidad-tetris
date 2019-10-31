package com.example.protetris;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;


import java.util.List;


public class UpcomingPiece extends View {

    private final int NUM_TYPE = 7; //Number of pieces
    private MainBoard board;
    private List<Piece> pieces;
    private int color;


    private Bitmap[] nextPieceArray = new Bitmap[NUM_TYPE + 1];

    public UpcomingPiece(Context context) {
        super(context);
    }

    public UpcomingPiece(Context context, MainBoard board, int colornum) {
        super(context);
        this.board = board;
        pieces = board.getPieces();
        this.color = colornum;
    }
    public int drawNextBlocks(int row, int col,int num,Piece piece) {
        switch (num){
            case 0: {
                switch (piece.pieceBoard[row][col]) {
                    case Piece.NO_BLOCK:
                        return Color.parseColor("#0030272A");
                    case Piece.BLUE_BLOCK:
                        return Color.parseColor("#0087FC");
                    case Piece.RED_BLOCK:
                        return Color.parseColor("#FD2929");
                    case Piece.PURPLE_BLOCK:
                        return Color.parseColor("#9C00E2");
                    case Piece.BLUELIGHT_BLOCK:
                        return Color.parseColor("#00D1FE");
                    case Piece.YELLOW_BLOCK:
                        return Color.parseColor("#FDD401");
                    case Piece.ORANGE_BLOCK:
                        return Color.parseColor("#FD6801");
                    case Piece.GREEN_BLOCK:
                        return Color.parseColor("#03DF04");
                }
                break;
            }
            case 1: {
                switch (piece.pieceBoard[row][col]) {
                    case Piece.NO_BLOCK:
                        return Color.parseColor("#0030272A");
                    case Piece.BLUE_BLOCK:
                        return Color.parseColor("#0087FC");
                    case Piece.RED_BLOCK:
                        return Color.parseColor("#0087FC");
                    case Piece.PURPLE_BLOCK:
                        return Color.parseColor("#0087FC");
                    case Piece.BLUELIGHT_BLOCK:
                        return Color.parseColor("#0087FC");
                    case Piece.YELLOW_BLOCK:
                        return Color.parseColor("#0087FC");
                    case Piece.ORANGE_BLOCK:
                        return Color.parseColor("#0087FC");
                    case Piece.GREEN_BLOCK:
                        return Color.parseColor("#0087FC");

                }
                break;
            }
            case 2: {
                switch (piece.pieceBoard[row][col]) {
                    case Piece.NO_BLOCK:
                        return Color.parseColor("#0030272A");
                    case Piece.BLUE_BLOCK:
                        return Color.parseColor("#FD2929");
                    case Piece.RED_BLOCK:
                        return Color.parseColor("#FD2929");
                    case Piece.PURPLE_BLOCK:
                        return Color.parseColor("#FD2929");
                    case Piece.BLUELIGHT_BLOCK:
                        return Color.parseColor("#FD2929");
                    case Piece.YELLOW_BLOCK:
                        return Color.parseColor("#FD2929");
                    case Piece.ORANGE_BLOCK:
                        return Color.parseColor("#FD2929");
                    case Piece.GREEN_BLOCK:
                        return Color.parseColor("#FD2929");

                }
                break;
            }
            case 3: {
                switch (piece.pieceBoard[row][col]) {
                    case Piece.NO_BLOCK:
                        return Color.parseColor("#0030272A");
                    case Piece.BLUE_BLOCK:
                        return Color.parseColor("#00D1FE");
                    case Piece.RED_BLOCK:
                        return Color.parseColor("#00D1FE");
                    case Piece.PURPLE_BLOCK:
                        return Color.parseColor("#00D1FE");
                    case Piece.BLUELIGHT_BLOCK:
                        return Color.parseColor("#00D1FE");
                    case Piece.YELLOW_BLOCK:
                        return Color.parseColor("#00D1FE");
                    case Piece.ORANGE_BLOCK:
                        return Color.parseColor("#00D1FE");
                    case Piece.GREEN_BLOCK:
                        return Color.parseColor("#00D1FE");

                }
                break;
            }
            case 4: {
                switch (piece.pieceBoard[row][col]) {
                    case Piece.NO_BLOCK:
                        return Color.parseColor("#0030272A");
                    case Piece.BLUE_BLOCK:
                        return Color.parseColor("#9C00E2");
                    case Piece.RED_BLOCK:
                        return Color.parseColor("#9C00E2");
                    case Piece.PURPLE_BLOCK:
                        return Color.parseColor("#9C00E2");
                    case Piece.BLUELIGHT_BLOCK:
                        return Color.parseColor("#9C00E2");
                    case Piece.YELLOW_BLOCK:
                        return Color.parseColor("#9C00E2");
                    case Piece.ORANGE_BLOCK:
                        return Color.parseColor("#9C00E2");
                    case Piece.GREEN_BLOCK:
                        return Color.parseColor("#9C00E2");

                }
                break;
            }
            case 5: {
                switch (piece.pieceBoard[row][col]) {
                    case Piece.NO_BLOCK:
                        return Color.parseColor("#0030272A");
                    case Piece.BLUE_BLOCK:
                        return Color.parseColor("#FDD401");
                    case Piece.RED_BLOCK:
                        return Color.parseColor("#FDD401");
                    case Piece.PURPLE_BLOCK:
                        return Color.parseColor("#FDD401");
                    case Piece.BLUELIGHT_BLOCK:
                        return Color.parseColor("#FDD401");
                    case Piece.YELLOW_BLOCK:
                        return Color.parseColor("#FDD401");
                    case Piece.ORANGE_BLOCK:
                        return Color.parseColor("#FDD401");
                    case Piece.GREEN_BLOCK:
                        return Color.parseColor("#FDD401");

                }
                break;
            }
            case 6: {
                switch (piece.pieceBoard[row][col]) {
                    case Piece.NO_BLOCK:
                        return Color.parseColor("#0030272A");
                    case Piece.BLUE_BLOCK:
                        return Color.parseColor("#FD6801");
                    case Piece.RED_BLOCK:
                        return Color.parseColor("#FD6801");
                    case Piece.PURPLE_BLOCK:
                        return Color.parseColor("#FD6801");
                    case Piece.BLUELIGHT_BLOCK:
                        return Color.parseColor("#FD6801");
                    case Piece.YELLOW_BLOCK:
                        return Color.parseColor("#FD6801");
                    case Piece.ORANGE_BLOCK:
                        return Color.parseColor("#FD6801");
                    case Piece.GREEN_BLOCK:
                        return Color.parseColor("#FD6801");

                }
                break;
            }
            case 7: {
                switch (piece.pieceBoard[row][col]) {
                    case Piece.NO_BLOCK:
                        return Color.parseColor("#0030272A");
                    case Piece.BLUE_BLOCK:
                        return Color.parseColor("#03DF04");
                    case Piece.RED_BLOCK:
                        return Color.parseColor("#03DF04");
                    case Piece.PURPLE_BLOCK:
                        return Color.parseColor("#03DF04");
                    case Piece.BLUELIGHT_BLOCK:
                        return Color.parseColor("#03DF04");
                    case Piece.YELLOW_BLOCK:
                        return Color.parseColor("#03DF04");
                    case Piece.ORANGE_BLOCK:
                        return Color.parseColor("#03DF04");
                    case Piece.GREEN_BLOCK:
                        return Color.parseColor("#03DF04");

                }
                break;
            }
        }
        return Color.parseColor("#0030272A");
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int ancho = (int) (this.getResources().getDisplayMetrics().widthPixels * 0.068);
        int alto = (int) (this.getResources().getDisplayMetrics().heightPixels * 0.04375);


        if (!pieces.isEmpty()) {
            Piece piece = board.getNextPiece();

            if (piece.getTetromino() == Piece.RED_BLOCK) {
                for (int row = 0; row < Piece.I_LENGTH; row++) {
                    for (int col = 0; col < Piece.I_LENGTH; col++) {
                        int blocks = this.drawNextBlocks(row, col, color,piece);

                        if (Piece.NO_BLOCK != piece.pieceBoard[row][col]) {
                            MainGame.loadBlocks(blocks, paint, col, row, alto, ancho, canvas);
                        }

                    }
                }
            } else {
                for (int row = 0; row < Piece.LENGTH; row++) {
                    for (int col = 0; col < Piece.LENGTH; col++) {
                        int blocks = this.drawNextBlocks(row, col, color,piece);

                        if (Piece.NO_BLOCK != piece.pieceBoard[row][col]) {
                            MainGame.loadBlocks(blocks, paint, col, row, alto, ancho, canvas);
                        }

                    }
                }
            }
        }
    }
}