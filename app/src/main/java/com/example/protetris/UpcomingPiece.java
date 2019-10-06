package com.example.protetris;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;


import java.util.List;


public class UpcomingPiece extends View {

    private final int NUM_TYPE = 7; //Number of pieces
    private MainBoard board;
    private List<Piece> pieces;
    Paint draw = new Paint();

    private Bitmap [] nextPieceArray = new Bitmap[NUM_TYPE + 1];

    public UpcomingPiece(Context context) {
        super(context);
    }

    public UpcomingPiece(Context context, MainBoard board) {
        super(context);
        this.board = board;
        pieces = board.getPieces();
        initPiecesView();
    }

    public void initPiecesView() {
        Resources res = this.getContext().getResources();
        loadNextPiece(Piece.BLUE_BLOCK, res.getDrawable(R.drawable.piece_s));
        loadNextPiece(Piece.RED_BLOCK, res.getDrawable(R.drawable.piece_i));
        loadNextPiece(Piece.PURPLE_BLOCK, res.getDrawable(R.drawable.piece_j));
        loadNextPiece(Piece.BLUELIGHT_BLOCK, res.getDrawable(R.drawable.piece_o));
        loadNextPiece(Piece.YELLOW_BLOCK, res.getDrawable(R.drawable.piece_l));
        loadNextPiece(Piece.ORANGE_BLOCK, res.getDrawable(R.drawable.piece_z));
        loadNextPiece(Piece.GREEN_BLOCK, res.getDrawable(R.drawable.piece_t));
    }

    public void loadNextPiece(int k, Drawable piece) {
        //ARGB_8888: cada pixel es almacenado en 4 bytes.
        Bitmap bitmap = Bitmap.createBitmap(130, 130, Bitmap.Config.ARGB_8888);
        piece.setBounds(0,0,130,130);
        Canvas canvas = new Canvas(bitmap);
        piece.draw(canvas);
        nextPieceArray[k] = bitmap;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!pieces.isEmpty()) {
            Piece piece = board.getNextPiece();

            switch (piece.getTetromino()) {
                case 1:
                    canvas.drawBitmap(nextPieceArray[Piece.BLUE_BLOCK], 20, 20, draw);
                    break;
                case 2:
                    canvas.drawBitmap(nextPieceArray[Piece.RED_BLOCK], 20, 20, draw);
                    break;
                case 3:
                    canvas.drawBitmap(nextPieceArray[Piece.PURPLE_BLOCK], 20, 20, draw);
                    break;
                case 4:
                    canvas.drawBitmap(nextPieceArray[Piece.BLUELIGHT_BLOCK], 20, 20, draw);
                    break;
                case 5:
                    canvas.drawBitmap(nextPieceArray[Piece.YELLOW_BLOCK], 20, 20, draw);
                    break;
                case 6:
                    canvas.drawBitmap(nextPieceArray[Piece.ORANGE_BLOCK], 20, 20, draw);
                    break;
                case 7:
                    canvas.drawBitmap(nextPieceArray[Piece.GREEN_BLOCK], 20, 20, draw);
                    break;
            }
        }
    }
}
