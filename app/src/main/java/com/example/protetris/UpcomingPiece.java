package com.example.protetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;


import java.util.List;


public class UpcomingPiece extends View {

    private MainBoard board;
    private List<Piece> pieces;
    private int color;
    private RelativeLayout nextPiece;

    public UpcomingPiece(Context context) {
        super(context);
    }

    public UpcomingPiece(Context context, MainBoard board, int color, RelativeLayout nextPiece) {
        super(context);
        this.board = board;
        pieces = board.getPieces();
        this.color = color;
        this.nextPiece = nextPiece;
    }

    public void setColor(int color) {
        this.color = color;
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
                if (piece.pieceBoard[row][col] == Piece.NO_BLOCK) {
                    return Color.parseColor("#0030272A");
                }
                return Color.parseColor("#0087FC");
            }
            case 2: {
                if (piece.pieceBoard[row][col] == Piece.NO_BLOCK) {
                    return Color.parseColor("#0030272A");
                }
                return Color.parseColor("#FD2929");
            }
            case 3: {
                if (piece.pieceBoard[row][col] == Piece.NO_BLOCK) {
                    return Color.parseColor("#0030272A");
                }
                return Color.parseColor("#00D1FE");
            }
            case 4: {
                if (piece.pieceBoard[row][col] == Piece.NO_BLOCK) {
                    return Color.parseColor("#0030272A");
                }
                return Color.parseColor("#9C00E2");
            }
            case 5: {
                if (piece.pieceBoard[row][col] == Piece.NO_BLOCK) {
                    return Color.parseColor("#0030272A");
                }
                return Color.parseColor("#FDD401");
            }
            case 6: {
                if (piece.pieceBoard[row][col] == Piece.NO_BLOCK) {
                    return Color.parseColor("#0030272A");
                }
                return Color.parseColor("#FD6801");
            }
            case 7: {
                if (piece.pieceBoard[row][col] == Piece.NO_BLOCK) {
                    return Color.parseColor("#0030272A");
                }
                return Color.parseColor("#03DF04");
            }
        }
        return Color.parseColor("#0030272A");
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        int ancho = (int) (nextPiece.getWidth() * 0.9 / 3);
        int alto = ((nextPiece.getHeight()  / 3) + 5);


        if (!pieces.isEmpty()) {
            Piece piece = board.getNextPiece();

            if (piece.getTetromino() == Piece.RED_BLOCK) {
                for (int row = 0; row < Piece.I_LENGTH; row++) {
                    for (int col = 0; col < Piece.I_LENGTH; col++) {

                        ancho = (int) (nextPiece.getWidth() * 0.8 / 3);
                        alto = (nextPiece.getHeight()  / 4) - 3;

                        int blocks = this.drawNextBlocks(row, col, color,piece);
                        //Pieza I
                        if (Piece.NO_BLOCK != piece.pieceBoard[row][col]) {
                            MainGame.loadBlocks(blocks, paint, row - 1, col, alto, ancho, canvas);
                        }

                    }
                }
            } else {
                for (int row = 0; row < Piece.LENGTH; row++) {
                    for (int col = 0; col < Piece.LENGTH; col++) {
                        int blocks = this.drawNextBlocks(row, col, color,piece);

                        if (Piece.NO_BLOCK != piece.pieceBoard[row][col]) {
                            if (piece.pieceBoard[row][col] == Piece.PURPLE_BLOCK || piece.pieceBoard[row][col] == piece.YELLOW_BLOCK) {
                                //Para compensar las piezas que no estan centradas, la pieza J y L
                                MainGame.loadBlocks(blocks, paint, col, row - 1, alto, ancho, canvas);
                            } else if (piece.pieceBoard[row][col] == Piece.BLUELIGHT_BLOCK){
                                //Para hacer más grande la pieza O
                                ancho = (int) (nextPiece.getWidth() * 0.7 / 2);
                                alto = (int) (nextPiece.getWidth() * 0.7 / 2);
                                MainGame.loadBlocks(blocks, paint, col, row, alto, ancho, canvas);
                            }else {
                                MainGame.loadBlocks(blocks, paint, col, row, alto, ancho, canvas);
                            }
                        }

                    }
                }
            }
        }
    }
}