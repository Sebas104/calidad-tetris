package com.example.protetris;


import android.graphics.Color;
import java.util.LinkedList;
import java.util.List;

public class MainBoard {

    //Constantes y variables
    private static final int EMPTY = 0;
    private static final int S_PIECE = 1;
    private static final int I_PIECE = 2;
    private static final int J_PIECE = 3;
    private static final int O_PIECE = 4;
    private static final int L_PIECE = 5;
    private static final int Z_PIECE = 6;
    private static final int T_PIECE = 7;

    private final int BOARD_NUM_ROWS = 20;
    private final int BOARD_NUM_COLS = 10;

    private final int NUM_TYPE = 7; //Numero de diferentes piezas
    private final int FIRST = 0; //Para coger la primera pieza del LinkedList de pieces

    private List<Piece> pieces = new LinkedList<>();

    private int actualRows = BOARD_NUM_ROWS;

    private int board[][]; //Tablero del juego

    public MainBoard() {
        board = new int[BOARD_NUM_ROWS][BOARD_NUM_COLS];
        this.resetBoard(this.board);

        int pieceRandom1 = (int) (Math.random() * NUM_TYPE) + 1;
        int pieceRandom2 = (int) (Math.random() * NUM_TYPE) + 1;
        pieces.add(new Piece(pieceRandom1));
        pieces.add(new Piece(pieceRandom2));
    }

    public int [][] getBoard() {
        return this.board;
    }

    public int getBOARD_NUM_ROWS() {
        return this.BOARD_NUM_ROWS;
    }

    public int getBOARD_NUM_COLS() {
        return this.BOARD_NUM_COLS;
    }

    public int getActualRows() {
        return this.actualRows;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Piece getActualPiece() {
        return pieces.get(FIRST);
    }

    public Piece getNextPiece() {
        return pieces.get(FIRST + 1);
    }

    public void resetBoard(int [][] board) {
        for (int row = 0; row < actualRows; row++) {
            for (int col = 0; col < BOARD_NUM_COLS; col++) {
                board[row][col] = 0; //Inicializa el tablero
            }
        }
    }

    public boolean checkGameOver(Piece actualPiece) {
        Coordinates newXY = actualPiece.copyCoord(actualPiece.coord);
        newXY.updateCoord(1, 0);
        if (actualPiece.checkCollision(this.board,newXY) &&
                actualPiece.getMinX(actualPiece.coord) < 2) {
            return true;
        }
        return false;
    }

    //Para dibujar los bloques en el tablero
    public int drawBlocks(int row, int col) {
        switch (board[row][col]) {
            case EMPTY:
                return Color.parseColor("#0030272A");
            case S_PIECE:
                return Color.parseColor("#0087FC");
            case I_PIECE:
                return Color.parseColor("#FD2929");
            case J_PIECE:
                return Color.parseColor("#9C00E2");
            case O_PIECE:
                return Color.parseColor("#00D1FE");
            case L_PIECE:
                return Color.parseColor("#FDD401");
            case Z_PIECE:
                return Color.parseColor("#FD6801");
            case T_PIECE:
                return Color.parseColor("#03DF04");

        }
        return Color.parseColor("#0030272A");
    }

    public int removeCompleteLines() {
        int linesToRemove = 0;

        for (int row = 0; row < actualRows; row++) {
            int rowComplete = 0;
            for (int col = 0; col < BOARD_NUM_COLS; col++) {
                if (this.board[row][col] > 0) {
                    rowComplete++;
                }
            }
            if (rowComplete == BOARD_NUM_COLS) {
                for (int row1 = row; row1 > 0; row1--) {
                    for (int col1 = 0; col1 < BOARD_NUM_COLS; col1++) {
                        this.board[row1][col1] = this.board[row1 - 1][col1];
                    }
                }
                linesToRemove++;
            }
        }
        return linesToRemove;
    }

    public void removePiece(Piece actualPiece, int [][] board) {
        board[actualPiece.coord.getCoord1().x][actualPiece.coord.getCoord1().y] = actualPiece.getNoBlock();
        board[actualPiece.coord.getCoord2().x][actualPiece.coord.getCoord2().y] = actualPiece.getNoBlock();
        board[actualPiece.coord.getCoord3().x][actualPiece.coord.getCoord3().y] = actualPiece.getNoBlock();
        board[actualPiece.coord.getCoord4().x][actualPiece.coord.getCoord4().y] = actualPiece.getNoBlock();
    }

    public void addPiece(Piece actualPiece, int [][] board) {
        board[actualPiece.coord.getCoord1().x][actualPiece.coord.getCoord1().y] = actualPiece.getTetromino();
        board[actualPiece.coord.getCoord2().x][actualPiece.coord.getCoord2().y] = actualPiece.getTetromino();
        board[actualPiece.coord.getCoord3().x][actualPiece.coord.getCoord3().y] = actualPiece.getTetromino();
        board[actualPiece.coord.getCoord4().x][actualPiece.coord.getCoord4().y] = actualPiece.getTetromino();
    }


    public void rotate(Piece actualPiece) {
        Piece checkPiece = new Piece(actualPiece);
        if ((actualPiece.rotatePiece(this.board, checkPiece.coord))) {
            removePiece(actualPiece, this.board);
            addPiece(checkPiece, this.board);
            actualPiece.updateAfterRotate(this.board, checkPiece.coord);
        }
    }

    public void moveToLeft(Piece actualPiece) {
        Coordinates newXY = actualPiece.copyCoord(actualPiece.coord);
        newXY.updateCoord(0, -1);
        if (!actualPiece.checkCollision(this.board, newXY)) {
            removePiece(actualPiece, this.board);
            actualPiece.moveCoord(0, -1);
            addPiece(actualPiece, this.board);
        }
    }

    public void moveToRight(Piece actualPiece) {
        Coordinates newXY = actualPiece.copyCoord(actualPiece.coord);
        newXY.updateCoord(0, 1);
        if (!actualPiece.checkCollision(this.board, newXY)) {
            removePiece(actualPiece, this.board);
            actualPiece.moveCoord(0, 1);
            addPiece(actualPiece, this.board);
        }
    }

    public boolean moveOneDown(Piece actualPiece) {
        Coordinates newXY = actualPiece.copyCoord(actualPiece.coord);
        newXY.updateCoord(1, 0);
        if (!actualPiece.checkCollision(this.board, newXY)) {
            removePiece(actualPiece, this.board);
            actualPiece.moveCoord(1, 0);
            addPiece(actualPiece, this.board);
            return true;
        }
        return false;
    }

    public void moveDown(Piece actualPiece) {
        Piece newPosition = new Piece(actualPiece);
        newPosition.coord = actualPiece.copyCoord(actualPiece.coord);
        newPosition.moveCoord(1, 0);
        while (!actualPiece.checkCollision(this.board, newPosition.coord)) {
            removePiece(actualPiece, this.board);
            addPiece(newPosition, this.board);
            newPosition.coord.updateCoord(1, 0);
            actualPiece.moveCoord(1, 0);
        }
    }

    public void reduceBoard() {

        Piece actualPiece = this.getActualPiece();

        Coordinates newXY = actualPiece.copyCoord(actualPiece.coord);
        newXY.updateCoord(2, 0);

        //Pieza en la fila 0 del tablero
        if (actualPiece.getCoord1().x == 0 || actualPiece.getCoord2().x == 0 || actualPiece.getCoord3().x == 0 || actualPiece.getCoord4().x == 0) {
            if (!actualPiece.checkCollision(this.board, newXY)) {
                actualPiece.moveCoord(1,0);
            }
        }

        //Pieza en la fila 1 del tablero
        if (actualPiece.getCoord1().x == 1 || actualPiece.getCoord2().x == 1 || actualPiece.getCoord3().x == 1 || actualPiece.getCoord4().x == 1) {
            if (!actualPiece.checkCollision(this.board, newXY)) {
                actualPiece.moveCoord(1,0);
            }
        }

        //Reducimos las filas del tablero en 2
        this.actualRows = this.actualRows - 2;

        //Proceso para actualizar el tablero con dos filas menos
        int [][] newBoard = new int[actualRows][BOARD_NUM_COLS];

        for (int row = 2; row < this.board.length; row++) {
            System.arraycopy(this.board[row], 0, newBoard[row - 2], 0,this.board[row].length);
        }

        this.board = newBoard;

        //Una vez tenemos el tablero actualizado, comprobamos el caso en que las piezas están al principio del tablero, entonces lo movemos hasta que no haya colision
        actualPiece.moveCoord(-2, 0);

        if (actualPiece.checkCollision(this.board,actualPiece.coord)) {
            actualPiece.moveCoord(1,0);
            while (actualPiece.checkCollision(this.board, actualPiece.coord)) {
                actualPiece.moveCoord(1, 0);
            }
            this.addPiece(actualPiece,this.board);
            return;
        }
        this.moveOneDown(actualPiece);
        this.moveOneDown(actualPiece);

    }
}