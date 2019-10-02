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

    public void clearRow(int r) {
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < 10; col++) {
                board[col][row] = board[col][row - 1];
            }
        }
        for (int col = 0; col < NUM_ROW; col++) {
            board[col][0] = Block.EMPTY_BLOCK;
        }
    }

    public void setBoardValue(int x, int y, int number){
        board[x][y] = number;
    }

    public int checkLine() {
        boolean complete = true;
        int numLines = 0;
        for (int r = 0; r < NUM_COL; r++) {
            for (int c = 0; c < NUM_ROW; c++) {
                if (board[c][r] == Block.EMPTY_BLOCK) {
                    complete = false;
                }
            }
            if (complete) {
                clearRow(r);
                numLines++;
            }
            complete = true;
        }
        return numLines;
    }

    public int getBoardValue(int col, int row) {
        return board[col][row];
    }

    public void copyBoard(Board copyBoard) {
        for (int c = 0; c < Board.NUM_ROW; c++) {
            for (int r = 0; r < Board.NUM_COL; r++) {
                this.board[c][r] = copyBoard.getBoardValue(c, r);
            }
        }
    }

    /*
    Check if there is contact with other objects on the board
    and put the pieces on the board
     */
    public boolean insertPiece(Piece piece) {
        for (int c = 0; c < piece.getS(); c++) {
            for (int r = 0; r < piece.getS(); r++) {
                if (piece.nBoard[c][r] != Block.EMPTY_BLOCK) {
                    if (piece.getnPosX() + c >= 0 && piece.getnPosX() + c < Board.NUM_ROW
                    && piece.getnPosY() + r >= 0 && piece.getnPosY() + r < Board.NUM_COL &&
                            (board[piece.getnPosX() + c][piece.getnPosY() + r] == Block.EMPTY_BLOCK
                            || board[piece.getnPosX() + c][piece.getnPosY() + r] == Block.SHADOW_BLOCK)) {
                        board[piece.getnPosX() + c][piece.getnPosY() + r] = piece.sBoard[c][r];
                    }
                    else {
                        return false;
                    }
                    if (!piece.inShadow()) {
                        if (piece.sBoard[c][r] != Block.EMPTY_BLOCK) {
                            board[piece.getsPosX() + c][piece.getsPosY() + r] = piece.sBoard[c][r];
                        }
                    }
                }
            }
        }
        return true;
    }
}
