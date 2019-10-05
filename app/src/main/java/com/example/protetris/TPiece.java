package com.example.protetris;

public class TPiece extends Piece {

    public TPiece() {
        super();
        this.pieceBoard[2][0] = GREEN_BLOCK;
        this.pieceBoard[1][1] = GREEN_BLOCK;
        this.pieceBoard[2][1] = GREEN_BLOCK;
        this.pieceBoard[2][2] = GREEN_BLOCK;
    }
}
