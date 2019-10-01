package com.example.protetris;

public class Board {
    public static final int NUM_ROW = 10;
    public static final int NUM_COL = 20;
    private int [][] board;

    public Board() {
        board = new int[NUM_ROW][NUM_COL];
        this.resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COL; j++) {
                board[i][j] = 0;
            }
        }
    }

    /*
    Check if there is contact with other objects on the board
    and put the pieces on the board
     */
    public boolean insertPiece() {
        return true;
    }
}
