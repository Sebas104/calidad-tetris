package com.example.protetris;

import android.graphics.Point;

public class Piece {

    //Constants
    public static final int S = 3; //Size piece

    //Variables
    public static boolean notVisible = true; //Shadow piece
    public int[][] iBoard; //Board of shadow pieces.
    public int[][] nBoard; //Normal Board
    public Point iPos; //Position of shadow pieces.
    private Point nPos; //Position on Board

    //Methods

    //Return the x coordinate of shadow pieces
    public int getiPosX() {
        return iPos.x;
    }

    //Return the y coordinate of shadow pieces
    public int getiPosY() {
        return iPos.y;

    }

    public Piece(int x, int y){
        nBoard = new int[S][S];
        iBoard = new int[S][S];

    }

    private void initPiece(int pieceSize){
        for (int column = 0; column < pieceSize; column++) {
            for (int row = 0; row < pieceSize; row++) {

            }
        }
    }
}
