package com.example.protetris;

public class LPiece extends Piece {

    public LPiece() {
        super();
        this.pieceBoard[2][0] = YELLOW_BLOCK;
        this.pieceBoard[2][1] = YELLOW_BLOCK;
        this.pieceBoard[2][2] = YELLOW_BLOCK;
        this.pieceBoard[1][2] = YELLOW_BLOCK;
    }
}
