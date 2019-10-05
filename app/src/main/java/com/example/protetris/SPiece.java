package com.example.protetris;

public class SPiece extends Piece {

    public SPiece() {
        super();
        this.pieceBoard[1][0] = BLUE_BLOCK;
        this.pieceBoard[0][1] = BLUE_BLOCK;
        this.pieceBoard[1][1] = BLUE_BLOCK;
        this.pieceBoard[0][2] = BLUE_BLOCK;
    }
}
