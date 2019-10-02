package com.example.protetris;

public class JPiece extends Piece {

    private static final int TYPE_BLOCK = Block.PURPLE_BLOCK;

    public JPiece(int x, int y) {
        super(x, y);
        initPiece();
        initShadow();
    }

    private void initPiece() {
        for (int c = 0; c < getS(); c++) {
            for (int r = 0; r < getS(); r++) {
                nBoard[c][r] = 0;
            }
        }
        this.nBoard[1][0] = TYPE_BLOCK;
        this.nBoard[1][1] = TYPE_BLOCK;
        this.nBoard[1][2] = TYPE_BLOCK;
        this.nBoard[0][2] = TYPE_BLOCK;
    }
}
