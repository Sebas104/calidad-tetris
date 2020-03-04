package com.example.protetris;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class MainBoardTest {

    private static final int EMPTY = 0;
    private static final int S_PIECE = 1;
    private static final int I_PIECE = 2;
    private static final int J_PIECE = 3;
    private static final int O_PIECE = 4;
    private static final int L_PIECE = 5;
    private static final int Z_PIECE = 6;
    private static final int T_PIECE = 7;
    private static final int SHADOW = 8;

    private final int BOARD_NUM_ROWS = 20;
    private final int BOARD_NUM_COLS = 10;

    private final int NUM_TYPE = 7; //Numero de diferentes piezas
    private final int FIRST = 0; //Para coger la primera pieza del LinkedList de pieces

    private Piece shadowActualPiece;
    private Piece shadowRandomPiece;
    private List<Piece> pieces = new LinkedList<>();

    private int actualRows = BOARD_NUM_ROWS;

    private int board[][];

    public void setBoard(int [][] board){
        this.board = board;
    }

    public int [][] rellenarTablero(){
        int [][] tablero = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];

        for(int i = 0; i < BOARD_NUM_ROWS; i++){
            for(int j = 0; j < BOARD_NUM_COLS; j++){
                tablero[i][j] = 7;
            }
        }
        return tablero;
    }

    public int [][] rellenarTableroDosLineas(){
        int [][] tablero = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];

        for(int i = BOARD_NUM_ROWS - 2; i < BOARD_NUM_ROWS; i++){
            for(int j = 0; j < BOARD_NUM_COLS; j++){
                tablero[i][j] = 7;
            }
        }
        return tablero;
    }

    @Test
    public void resetBoard() {
    }

    @Test
    public void checkGameOverFalse() {

        try{

            MainBoard mainBoard = new MainBoard();

            int pieceRandom1 = (int) (Math.random() * NUM_TYPE) + 1;
            Piece piece = new Piece(pieceRandom1);

            boolean result = mainBoard.checkGameOver(piece);

            assertFalse(result);

        } catch (Exception e){

        }
    }

    @Test
    public void checkGameOverTrue() {

        try{

            int [][] auxBoard = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];

            auxBoard = rellenarTablero();

            MainBoard mainBoard = new MainBoard();

            mainBoard.setBoard(auxBoard);

            int pieceRandom1 = (int) (Math.random() * NUM_TYPE) + 1;
            Piece piece = new Piece(pieceRandom1);

            boolean result = mainBoard.checkGameOver(piece);

            assertTrue(result);

        } catch (Exception e){

        }
    }

    @Test
    public void drawBlocks() {
    }

    @Test
    public void removeCompleteLinesZero() {

        MainBoard mainBoard = new MainBoard();

        int pieceRandom1 = (int) (Math.random() * NUM_TYPE) + 1;
        Piece piece = new Piece(pieceRandom1);

        int result = mainBoard.removeCompleteLines(piece);

        assertEquals(0, result);

    }

    @Test
    public void removeCompleteLinesTwo() {

        int [][] auxBoard = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];

        auxBoard = rellenarTableroDosLineas();

        MainBoard mainBoard = new MainBoard();

        mainBoard.setBoard(auxBoard);

        int pieceRandom1 = (int) (Math.random() * NUM_TYPE) + 1;
        Piece piece = new Piece(pieceRandom1);

        int result = mainBoard.removeCompleteLines(piece);

        assertEquals(2, result);

    }

    @Test
    public void removePiece() {
    }

    @Test
    public void addPiece() {

        MainBoard mainBoard = new MainBoard();

        Piece piece = new Piece(2);

        int [][] auxBoard = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];
        int [][] resultBoard = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];

        auxBoard[0][3] = 2;
        auxBoard[0][4] = 2;
        auxBoard[0][5] = 2;
        auxBoard[0][6] = 2;

        mainBoard.addPiece(piece, resultBoard);

        assertArrayEquals(resultBoard, auxBoard);

    }

    @Test
    public void rotate() {
    }

    @Test
    public void moveToLeft() {
    }

    @Test
    public void moveToRight() {
    }

    @Test
    public void moveOneDown() {
    }

    @Test
    public void moveDown() {
    }

    @Test
    public void fastFall() {
    }

    @Test
    public void shadowPiece() {
    }

    @Test
    public void reduceBoard() {
    }
}