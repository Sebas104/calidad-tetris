package com.example.protetris;

import android.graphics.Point;

public class Piece {

    //Constants
    public static final int S = 3; //Size piece


    //Variables
    public int[][] sBoard; //Board of shadow pieces.
    public int[][] nBoard; //Normal Board
    public Point sPos; //Position of shadow pieces.
    private Point nPos; //Position on Board

    //Methods

    //Return the x coordinate of shadow pieces
    public int getsPosX() {
        return sPos.x;
    }

    //Return the y coordinate of shadow pieces
    public int getsPosY() {
        return sPos.y;

    }

    public int getnPosY() {
        return nPos.y;

    }

    public int getnPosX() {
        return nPos.x;

    }

    public Piece(int x, int y){
        nBoard = new int[S][S];
        sBoard = new int[S][S];
        initPiece((S));
        nPos = new Point(x,y);
    }

    public void initShadow() {
        copyBoard(nBoard, sBoard, Piece.S);
        sPos.set(nPos.x, nPos.y);
        sSety();
    }

    public void initPiece(int pieceSize){
        for (int column = 0; column < pieceSize; column++) {
            for (int row = 0; row < pieceSize; row++) {
                sBoard[column][row] = Block.EMPTY_BLOCK;
                nBoard[column][row] = Block.EMPTY_BLOCK;
            }
        }
    }

    //Get size of piece
    public int getS() {
        return S;
    }

    public boolean inShadow() {
        if (nPos.y == sPos.y) {
            return true;
        }
        return false;
    }

    //Set y of shadow piece
    public void sSety() {
        for (sPos.y = this.nPos.y;
             !touchY(this.sPos.y+1, this.sPos.x, sBoard, MainBoard.notPieceBoard);
             this.sPos.y++);
    }

    public void copyBoard(int [][] originalBoard, int [][] copyBoard, int s) {
        for (int c = 0; c < s; c++) {
            for (int r = 0; r < s; r++) {
                if (originalBoard[c][r] != Block.EMPTY_BLOCK) {
                    copyBoard[c][r] = Block.SHADOW_BLOCK;
                }
            }
        }
    }

    public boolean touchY(int y1, int x1, int [][] pBoard, Board board) {
       if (y1 < Board.NUM_COL) {
           for (int c = 0; c < this.getS(); c++) {
               for (int r = 0; r < this.getS(); r++) {
                   if (pBoard[c][r] != Block.EMPTY_BLOCK) {
                       if ((x1 + c) >= 0 && (x1 + c) < Board.NUM_ROW) {
                           if (y1 + r >= Board.NUM_COL ||
                                   board.getBoardValue(x1 + c, y1 + r) != Block.EMPTY_BLOCK) {

                               return true;
                           }
                       }
                   }
               }
           }
       }
       else {
           return true;
       }
       return false;
    }

    public boolean touchX(int x1, int [][] pBoard, Board board) {
        if (x1 >= -1 && x1 < Board.NUM_ROW) {
            for (int c = 0; c < this.getS(); c++) {
                for (int r = 0; r < this.getS(); r++) {
                    if (pBoard[c][r] != Block.EMPTY_BLOCK) {
                        if (x1 + c >= Board.NUM_ROW || x1 + c < 0
                        || board.getBoardValue(x1 + c, this.nPos.y + r) !=
                        Block.EMPTY_BLOCK) {
                            return true;
                        }
                    }
                }
            }
        }
        else {
            return true;
        }
        return false;
    }

    public boolean rotatePiece(Board board) {
        int [][] aux = new int[S][S];
        for (int column = 0; column < S; column++) {
            for (int row = 0; row < S; row++) {
                aux[column][row] = nBoard[row][2-column];
            }
        }

        if (!touchX(this.nPos.x, aux, board) && !touchY(this.nPos.y, this.nPos.x, aux, board)) {
           nBoard = aux;
           resetShadow(S);
           copyBoard(aux, sBoard, S);
           sSety();
           return true;
        }
        else if (!touchX(this.nPos.x - 1, aux, board) && !touchY(this.nPos.y, this.nPos.x - 1, aux, board)) {
            this.nPos.x--;
            this.sPos.x--;
            sBoard = aux;
            resetShadow(S);
            copyBoard(aux, sBoard, S);
            sSety();
            return true;
        }
        else if (!touchX(this.nPos.x + 1, aux, board) && !touchY(this.nPos.y, this.nPos.x + 1, aux, board)) {
            this.sPos.x++;
            this.nPos.x++;
            sBoard = aux;
            resetShadow(S);
            copyBoard(aux, sBoard, S);
            return true;
        }
        return false;
    }

    //move the piece to the left
    public boolean goLeft(Board board) {
        if (!touchX(this.nPos.x-1, nBoard, board)) {
            this.sPos.x--;
            this.nPos.x--;
            sSety();
            return true;
        }
        return false;
    }

    //move the piece to the right
    public boolean goRight(Board board) {
        if (!touchX(this.nPos.x+1, nBoard, board)) {
            this.sPos.x++;
            this.nPos.x++;
            sSety();
            return true;
        }
        return false;
    }

    public boolean goDown(Board board) {
        if (!touchY(this.nPos.y+1, this.nPos.x, nBoard, board)) {
            this.nPos.y++;
            return true;
        } else {
            return false;
        }
    }

    /*
	public void drop(Board board) {
			this.pos.y = this.ghostPos.y;
	}
     */

    public void resetShadow(int pieceSize) {
        for (int column = 0; column < pieceSize; column++) {
            for (int row = 0; row < pieceSize; row++) {
                sBoard[column][row] = Block.EMPTY_BLOCK;
            }
        }
    }

    public boolean setnPos(int i, int j, Board board) {
        if (i >= 0 && i < Board.NUM_ROW) {
            for (int c = 0; c < this.getS(); c++) {
                for (int r = 0; r < this.getS(); r++) {
                    if (nBoard[c][r] != Block.EMPTY_BLOCK) {
                        if (i + c >= Board.NUM_ROW || i + c < 0
                        || j + r >= Board.NUM_COL || board.getBoardValue(i + c, j + r) != Block.EMPTY_BLOCK) {
                            return false;
                        }

                    }
                }
            }
        }
        this.nPos.x = i;
        this.nPos.y = j;
        return true;
    }

}
