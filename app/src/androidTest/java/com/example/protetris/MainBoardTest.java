package com.example.protetris;

import android.graphics.Color;

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

    public MainBoard mainBoard;




    public void initialicer(int value){

        this.mainBoard = new MainBoard();

        this.mainBoard.board = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        this.mainBoard.board[0][0] = value;
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
    public void resetBoardTest1() {
        this.mainBoard = new MainBoard();

        int[][] resultBoard = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        mainBoard.resetBoard(this.mainBoard.board);


        assertArrayEquals(this.mainBoard.board,resultBoard);
    }

    @Test
    public void resetBoardTest2(){
        this.mainBoard = new MainBoard();

        int[][] resultBoard = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        for (int row = 0; row < this.BOARD_NUM_ROWS; row++) {
            for (int col = 0; col < BOARD_NUM_COLS; col++) {
                this.mainBoard.board[row][col] = 1;
            }
        }

        mainBoard.resetBoard(this.mainBoard.board);

        assertArrayEquals(this.mainBoard.board,resultBoard);
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

    /*
        Solo testearemos el case 0 debido a la complejidad ciclomatica del metodo
     */

    @Test
    public void drawBlocksTest0() {

        this.initialicer(this.SHADOW);

        int resultSpected = Color.parseColor("#26F2F2F2");

        int result = this.mainBoard.drawBlocks(0,0,0);

        assertEquals(result,resultSpected);

    }

    @Test
    public void drawBlocksTest1(){

        this.initialicer(this.S_PIECE);

        int resultSpected = Color.parseColor("#0087FC");

        int result = this.mainBoard.drawBlocks(0,0,1);

        assertEquals(resultSpected,result);

    }

    @Test
    public void drawBlocksTest2(){

        this.initialicer(this.S_PIECE);

        int resultSpected = Color.parseColor("#FD2929");

        int result = this.mainBoard.drawBlocks(0,0,2);

        assertEquals(resultSpected,result);
    }

    @Test
    public void drawBlocksTest3(){

        this.initialicer(this.S_PIECE);

        int resultSpected = Color.parseColor("#00D1FE");

        int result = this.mainBoard.drawBlocks(0,0,3);

        assertEquals(resultSpected,result);
    }

    @Test
    public void drawBlocksTest4(){

        this.initialicer(this.S_PIECE);

        int resultSpected = Color.parseColor("#9C00E2");

        int result = this.mainBoard.drawBlocks(0,0,4);

        assertEquals(resultSpected,result);
    }

    @Test
    public void drawBlocksTest5(){

        this.initialicer(this.S_PIECE);

        int resultSpected = Color.parseColor("#FDD401");

        int result = this.mainBoard.drawBlocks(0,0,5);

        assertEquals(resultSpected,result);
    }

    @Test
    public void drawBlocksTest6(){

        this.initialicer(this.S_PIECE);

        int resultSpected = Color.parseColor("#FD6801");

        int result = this.mainBoard.drawBlocks(0,0,6);

        assertEquals(resultSpected,result);
    }

    @Test
    public void drawBlocksTest7(){

        this.initialicer(this.S_PIECE);

        int resultSpected = Color.parseColor("#03DF04");

        int result = this.mainBoard.drawBlocks(0,0,7);

        assertEquals(resultSpected,result);
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

        this.mainBoard = new MainBoard();

        Piece piece = new Piece(this.S_PIECE);

        this.mainBoard.addPiece(piece,this.mainBoard.board);

        this.mainBoard.removePiece(piece,this.mainBoard.board);

        int[][] result = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        assertArrayEquals(result,this.mainBoard.board);
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

        assertArrayEquals(auxBoard, resultBoard);

    }

    @Test
    public void rotate() {

        this.mainBoard = new MainBoard();

        Piece piece = new Piece(this.I_PIECE);
        this.mainBoard.addPiece(piece,this.mainBoard.board);

        this.mainBoard.moveOneDown(piece,true);
        this.mainBoard.moveOneDown(piece,true);
        //this.mainBoard.moveOneDown(piece,true);
        //this.mainBoard.moveOneDown(piece,true);

        this.mainBoard.rotate(piece,true);

        int[][] board = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        board[0][5] = 2;
        board[1][5] = 2;
        board[2][5] = 2;
        board[3][5] = 2;

        board[16][5] = 8;
        board[17][5] = 8;
        board[18][5] = 8;
        board[19][5] = 8;

        assertArrayEquals(board,this.mainBoard.board);
    }

    @Test
    public void moveToLeftPosition() {

        MainBoard mainBoard = new MainBoard();

        Piece piece = new Piece(2);

        mainBoard.addPiece(piece, mainBoard.getBoard());

        int [][] auxBoard = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];
        auxBoard[0][2] = 2;
        auxBoard[0][3] = 2;
        auxBoard[0][4] = 2;
        auxBoard[0][5] = 2;

        auxBoard[19][2] = 8;
        auxBoard[19][3] = 8;
        auxBoard[19][4] = 8;
        auxBoard[19][5] = 8;

        mainBoard.moveToLeft(piece, true);

        int [][] resultBoard = mainBoard.getBoard();

        assertArrayEquals(auxBoard, resultBoard);

    }

    @Test
    public void moveToRight() {

        this.mainBoard = new MainBoard();

        Piece piece = new Piece(this.I_PIECE);
        this.mainBoard.addPiece(piece, this.mainBoard.board);
        this.mainBoard.moveToRight(piece,true);

        int[][] board = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        board[0][4] = 2;
        board[0][5] = 2;
        board[0][6] = 2;
        board[0][7] = 2;

        board[19][4] = 8;
        board[19][5] = 8;
        board[19][6] = 8;
        board[19][7] = 8;

        assertArrayEquals(board,this.mainBoard.board);
    }

    @Test
    public void moveOneDown() {

        MainBoard mainBoard = new MainBoard();

        Piece piece = new Piece(2);

        mainBoard.addPiece(piece, mainBoard.getBoard());

        int [][] auxBoard = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];
        auxBoard[1][3] = 2;
        auxBoard[1][4] = 2;
        auxBoard[1][5] = 2;
        auxBoard[1][6] = 2;

        auxBoard[19][3] = 8;
        auxBoard[19][4] = 8;
        auxBoard[19][5] = 8;
        auxBoard[19][6] = 8;

        mainBoard.moveOneDown(piece, true);

        int [][] resultBoard = mainBoard.getBoard();

        assertArrayEquals(auxBoard, resultBoard);

    }

    @Test
    public void moveDown() {

        this.mainBoard = new MainBoard();

        Piece piece = new Piece(this.I_PIECE);
        this.mainBoard.addPiece(piece, this.mainBoard.board);
        this.mainBoard.moveDown(piece);

        int[][] board = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        board[19][3] = 2;
        board[19][4] = 2;
        board[19][5] = 2;
        board[19][6] = 2;

        assertArrayEquals(board, this.mainBoard.board);
    }

    @Test
    public void fastFall() {

        MainBoard mainBoard = new MainBoard();

        Piece piece = new Piece(2);

        mainBoard.addPiece(piece, mainBoard.getBoard());

        int [][] auxBoard = new int [BOARD_NUM_ROWS][BOARD_NUM_COLS];
        auxBoard[19][3] = 2;
        auxBoard[19][4] = 2;
        auxBoard[19][5] = 2;
        auxBoard[19][6] = 2;

        mainBoard.moveOneDown(piece, true);

        mainBoard.fastFall(piece, true);

        int [][] resultBoard = mainBoard.getBoard();

        assertArrayEquals(auxBoard, resultBoard);

    }

    @Test
    public void shadowPiece1() {

        this.mainBoard = new MainBoard();

        Piece piece = new Piece(this.I_PIECE);
        this.mainBoard.addPiece(piece,this.mainBoard.board);

        this.mainBoard.shadowPiece(piece,true);

        int[][] board = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        board[19][3] = 8;
        board[19][4] = 8;
        board[19][5] = 8;
        board[19][6] = 8;
    }

    @Test
    public void shadowPiece2(){
        this.mainBoard = new MainBoard();

        Piece piece = new Piece(this.I_PIECE);
        this.mainBoard.addPiece(piece,this.mainBoard.board);

        this.mainBoard.shadowPiece(piece,true);

        int[][] board = new int[this.BOARD_NUM_ROWS][this.BOARD_NUM_COLS];

        board[19][3] = 8;
        board[19][4] = 8;
        board[19][5] = 8;
        board[19][6] = 8;
    }

    @Test
    public void reduceBoard() {

        MainBoard mainBoard = new MainBoard();

        Piece piece = new Piece(2);

        mainBoard.addPiece(piece, mainBoard.getBoard());

        mainBoard.reduceBoard(piece);

        int [][] resultBoard = mainBoard.getBoard();

        int expectedLength = BOARD_NUM_ROWS - 2;

        int boardLength = resultBoard.length;

        assertEquals(expectedLength, boardLength);

    }
}