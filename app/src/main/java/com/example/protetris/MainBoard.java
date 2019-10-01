package com.example.protetris;

import android.content.Context;
import android.util.AttributeSet;
import android.os.Handler;

import static android.drm.DrmStore.Playback.PAUSE;

public class MainBoard extends Block {
    public static final int Z_PIECE = 0;
    public static final int S_PIECE = 1;
    public static final int O_PIECE = 2;
    public static final int I_PIECE = 3;
    public static final int J_PIECE = 4;
    public static final int L_PIECE = 5;
    public static final int T_PIECE = 6;

    private int count;
    private RefreshHandler redraw = new RefreshHandler();
    private long speedFall;
    private int stateTetris = PAUSE;
    private Piece actualPiece;

    public static Board actualBoard;
    public static Board notPieceBoard;
    public static Board lastBoard;

    private static int [] randomArray = {-1, -1};
    private boolean moved;

    //Constructors
    public MainBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        starMainBoard();
    }

    public MainBoard(Context context, AttributeSet attrs, int style) {
        super(context, attrs,style);
        starMainBoard();
    }

    private void starMainBoard() {

    }
}
