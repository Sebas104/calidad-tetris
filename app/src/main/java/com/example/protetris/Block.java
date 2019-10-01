package com.example.protetris;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class Block extends View {

    protected static final int EMPTY_BLOCK = 0;
    protected static final int RED_BLOCK = 1;
    protected static final int BLUE_BLOCK = 2;
    protected static final int GREEN_BLOCK = 3;
    protected static final int PURPLE_BLOCK = 4;
    protected static final int YELLOW_BLOCK = 5;
    protected static final int LIGHTBLUE_BLOCK =6;
    protected static final int ORANGE_BLOCK = 7;
    protected static final int GRAY_BLOCK = 8;
    protected static final int SHADOW_BLOCK = 9;
    protected static final int WHITE_BLOCK= 10;
    protected static final int NUM_BLOCKS = 10;

    //Ratio of the tetris board to the size of the view
    protected static final double ratio = 0.625;

    protected static int blockSize;

    //To count the number of tiles that will be shown
    protected static final int XBlockNumber = 10;
    protected static final int YBlockNumber = 20;

    protected static int XBalance;
    protected static int YBalance;

    /*
    An array that maps the integer specified in the subclass to the
    drawable that will be used for that reference
     */
    private Bitmap [] blockArray;
    private Bitmap [] nextPieceArray;

    /*
    The number represents the index of the block that has to be shown
    at that locations
     */
    private int [][] blockBoard;

    protected int next;
    private final Paint paint = new Paint();

    //Constructors
    public Block(Context context, AttributeSet attrs) {
        super(context, attrs);
        blockBoard = new int[XBlockNumber][YBlockNumber];
    }

    public Block(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        blockBoard= new int[XBlockNumber][YBlockNumber];
    }

    public void resetBlocks(int blockNumber) {
        blockArray = new Bitmap[blockNumber];
        nextPieceArray = new Bitmap[MainBoard.T_PIECE + 1];
    }

    protected void computeBlockSize(int x, int y) {
        blockSize = (int) Math.floor((x*ratio / XBlockNumber));
        XBalance = blockSize;
        YBalance = ((y - (blockSize * YBlockNumber)) / 2);
    }

    public void loadBlock(int k, Drawable block) {
        Bitmap b = Bitmap.createBitmap(blockSize, blockSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        block.setBounds(0,0,blockSize,blockSize);
        block.draw(canvas);
        blockArray[k] = b;
    }

    public void loadNextPiece(int k, Drawable piece) {
        Bitmap b = Bitmap.createBitmap(90,90, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(b);
        piece.setBounds(0,0,90,90);
        piece.draw(canvas);
        nextPieceArray[k] = b;
    }

    @Override
    protected void onSizeChanged(int x1, int y1, int x2, int y2) {
        computeBlockSize(x1,y1);
        Resources resource = this.getContext().getResources();
        loadBlock(GREEN_BLOCK, resource.getDrawable(R.drawable.green_square));
        loadBlock(YELLOW_BLOCK, resource.getDrawable(R.drawable.yellow_square));
        loadBlock(GRAY_BLOCK, resource.getDrawable(R.drawable.gray_square));
        loadBlock(RED_BLOCK, resource.getDrawable(R.drawable.red_square));
        loadBlock(BLUE_BLOCK, resource.getDrawable(R.drawable.blue_square));
        loadBlock(ORANGE_BLOCK, resource.getDrawable(R.drawable.orange_square));
        loadBlock(LIGHTBLUE_BLOCK, resource.getDrawable(R.drawable.bluelight_square));
        loadBlock(PURPLE_BLOCK, resource.getDrawable(R.drawable.purple_square));
        loadBlock(SHADOW_BLOCK, resource.getDrawable(R.drawable.shadow_square));

        loadNextPiece(MainBoard.Z_PIECE, resource.getDrawable(R.drawable.piece_z));
        loadNextPiece(MainBoard.S_PIECE, resource.getDrawable(R.drawable.piece_s));
        loadNextPiece(MainBoard.O_PIECE, resource.getDrawable(R.drawable.piece_o));
        loadNextPiece(MainBoard.I_PIECE, resource.getDrawable(R.drawable.piece_i));
        loadNextPiece(MainBoard.J_PIECE, resource.getDrawable(R.drawable.piece_j));
        loadNextPiece(MainBoard.L_PIECE, resource.getDrawable(R.drawable.piece_l));
        loadNextPiece(MainBoard.T_PIECE, resource.getDrawable(R.drawable.piece_t));
    }

    //Draw the block at the respective x and y coordinates
    public void setBlock(int type, int x, int y) {
        blockBoard[x][y] = type;
    }

    //Set all blocks to EMPTY_BLOCK
    public void clearBlocks() {
        for (int i = 0; i < XBlockNumber; i++) {
            for (int j = 0; j < YBlockNumber; j++) {
                setBlock(EMPTY_BLOCK, i, j);
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < XBlockNumber; i++) {
            for (int j = 0; j < YBlockNumber; j++) {
                if (blockBoard[i][j] > 0) {
                    canvas.drawBitmap(blockArray[blockBoard[i][j]],
                            XBalance + i*blockSize,
                            YBlockNumber + j*blockSize,
                            paint);

                }
            }
        }
        canvas.drawBitmap(nextPieceArray[next], 370, 140, paint);
    }
}
