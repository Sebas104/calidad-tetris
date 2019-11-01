package com.example.protetris;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Piece extends Coordinates{

    static final int NO_BLOCK = 0;
    static final int BLUE_BLOCK = 1; //Piece S
    static final int RED_BLOCK = 2; //Piece I
    static final int PURPLE_BLOCK = 3; //Piece J
    static final int BLUELIGHT_BLOCK = 4; //Piece O
    static final int YELLOW_BLOCK = 5; //Piece L
    static final int ORANGE_BLOCK = 6; //Piece Z
    static final int GREEN_BLOCK = 7; //Piece T

    static final int LENGTH = 3; //Tamaño de los array de las piezas, 4 por la pieza I
    static final int I_LENGTH = 4;
    int [][] pieceBoard; //Array de la pieza para el método rotar
    Coordinates coord;
    private int tetromino; //El tipo de bloque

    public Piece(Piece sourcePiece) {
        this.coord = new Coordinates();
        this.tetromino = sourcePiece.tetromino;
    }

    //Al crear cada pieza se inicializan al principio del array tablero
    public Piece(int pieceType) {

        this.coord = new Coordinates();

        if (pieceType == this.RED_BLOCK) { //La pieza I es un caso especial
            this.pieceBoard = new int[I_LENGTH][I_LENGTH];
            for (int col = 0; col < I_LENGTH; col++) {
                for (int row = 0; row < I_LENGTH; row++) {
                    this.pieceBoard[col][row] = NO_BLOCK;
                }
            }
        } else {
            this.pieceBoard = new int[LENGTH][LENGTH];
            for (int col = 0; col < LENGTH; col++) {
                for (int row = 0; row < LENGTH; row++) {
                    this.pieceBoard[col][row] = NO_BLOCK;
                }
            }
        }

        switch (pieceType) {
            case BLUE_BLOCK:
                this.pieceBoard[0][2] = BLUE_BLOCK;
                this.pieceBoard[0][1] = BLUE_BLOCK;
                this.pieceBoard[1][1] = BLUE_BLOCK;
                this.pieceBoard[1][0] = BLUE_BLOCK;

                this.coord.setCoord1(1, 3);
                this.coord.setCoord2(0, 4);
                this.coord.setCoord3(1, 4);
                this.coord.setCoord4(0, 5);
                this.tetromino = BLUE_BLOCK;
                break;

            case RED_BLOCK:
                this.pieceBoard[2][0] = RED_BLOCK;
                this.pieceBoard[2][1] = RED_BLOCK;
                this.pieceBoard[2][2] = RED_BLOCK;
                this.pieceBoard[2][3] = RED_BLOCK;

                this.coord.setCoord1(0, 3);
                this.coord.setCoord2(0, 4);
                this.coord.setCoord3(0, 5);
                this.coord.setCoord4(0, 6);
                this.tetromino = RED_BLOCK;
                break;

            case PURPLE_BLOCK:
                this.pieceBoard[1][0] = PURPLE_BLOCK;
                this.pieceBoard[2][0] = PURPLE_BLOCK;
                this.pieceBoard[2][1] = PURPLE_BLOCK;
                this.pieceBoard[2][2] = PURPLE_BLOCK;

                this.coord.setCoord1(0, 3);
                this.coord.setCoord2(1, 3);
                this.coord.setCoord3(1, 4);
                this.coord.setCoord4(1, 5);
                this.tetromino = PURPLE_BLOCK;
                break;

            case BLUELIGHT_BLOCK:
                this.pieceBoard[0][1] = BLUELIGHT_BLOCK;
                this.pieceBoard[0][2] = BLUELIGHT_BLOCK;
                this.pieceBoard[1][1] = BLUELIGHT_BLOCK;
                this.pieceBoard[1][2] = BLUELIGHT_BLOCK;
                this.coord.setCoord1(0, 4);
                this.coord.setCoord2(1, 4);
                this.coord.setCoord3(0, 5);
                this.coord.setCoord4(1, 5);
                this.tetromino = BLUELIGHT_BLOCK;
                break;

            case YELLOW_BLOCK:
                this.pieceBoard[2][0] = YELLOW_BLOCK;
                this.pieceBoard[2][1] = YELLOW_BLOCK;
                this.pieceBoard[2][2] = YELLOW_BLOCK;
                this.pieceBoard[1][2] = YELLOW_BLOCK;

                this.coord.setCoord1(1, 3);
                this.coord.setCoord2(1, 4);
                this.coord.setCoord3(0, 5);
                this.coord.setCoord4(1, 5);
                this.tetromino = YELLOW_BLOCK;
                break;

            case ORANGE_BLOCK:
                this.pieceBoard[0][0] = ORANGE_BLOCK;
                this.pieceBoard[0][1] = ORANGE_BLOCK;
                this.pieceBoard[1][1] = ORANGE_BLOCK;
                this.pieceBoard[1][2] = ORANGE_BLOCK;

                this.coord.setCoord1(0, 3);
                this.coord.setCoord2(0, 4);
                this.coord.setCoord3(1, 4);
                this.coord.setCoord4(1, 5);
                this.tetromino = ORANGE_BLOCK;
                break;

            case GREEN_BLOCK:
                this.pieceBoard[0][1] = GREEN_BLOCK;
                this.pieceBoard[1][1] = GREEN_BLOCK;
                this.pieceBoard[1][0] = GREEN_BLOCK;
                this.pieceBoard[1][2] = GREEN_BLOCK;

                this.coord.setCoord1(1, 3);
                this.coord.setCoord2(0, 4);
                this.coord.setCoord3(1, 4);
                this.coord.setCoord4(1, 5);
                this.tetromino = GREEN_BLOCK;
                break;
        }
    }


    public void moveCoord(int newRow, int newCol) {
        this.coord.updateCoord(newRow, newCol);
    }

    @Override
    public Point getCoord1() {
        return this.coord.coord1;
    }

    @Override
    public Point getCoord2() {
        return this.coord.coord2;
    }

    @Override
    public Point getCoord3() {
        return this.coord.coord3;
    }

    @Override
    public Point getCoord4() {
        return this.coord.coord4;
    }

    public int getTetromino() {
        return this.tetromino;
    }

    public int getNoBlock() {
        return this.NO_BLOCK;
    }

    public int getMinX(Coordinates coord) {
        return Math.min(Math.min(coord.coord1.x,coord.coord2.x), Math.min(coord.coord3.x, coord.coord4.x));
    }

    //En board en el metodo rotar guardamos todas las coord para poder borrarlas y recuperarlas sino se puede rotar
    public boolean rotatePiece(int [][] board, Coordinates newXY) {

        List<Point> newPoints = new LinkedList<>();

        int aux [][] = new int[LENGTH][LENGTH];
        int auxI [][] = new int[I_LENGTH][I_LENGTH];

        if (this.tetromino == this.BLUELIGHT_BLOCK) { //Pieza O
            return false;
        }
        else if (this.tetromino == this.RED_BLOCK) {
            for (int col = 0; col < I_LENGTH; col++) { //Se recorre primero las columnas
                for (int row = 0; row < I_LENGTH; row++) {
                    auxI[I_LENGTH - 1 - col][row] = this.pieceBoard[row][col];
                    if (this.pieceBoard[row][col] != NO_BLOCK) {
                        //Hacemos si row = 2 y col = 0 se lo restamos a
                        //las nuevas coordenadas que ha adquirido ese mismo bloque.
                        Point point = new Point(((I_LENGTH - 1 - col) - row), (row - col));
                        newPoints.add(point);
                    }
                }
            }
        } else {
            for (int col = 0; col < LENGTH; col++) { //Se recorre primero las columnas
                for (int row = 0; row < LENGTH; row++) {
                    aux[LENGTH - 1 - col][row] = this.pieceBoard[row][col];
                    if (this.pieceBoard[row][col] != NO_BLOCK) {
                        //Hacemos si row = 2 y col = 0 se lo restamos a
                        //las nuevas coordenadas que ha adquirido ese mismo bloque.
                        Point point = new Point(((LENGTH - 1 - col) - row), (row - col));
                        newPoints.add(point);
                    }
                }
            }
        }

            Point p1 = newPoints.remove(0);
            newXY.getCoord1().x = this.coord.getCoord1().x + p1.x;
            newXY.getCoord1().y = this.coord.getCoord1().y + p1.y;

            Point p2 = newPoints.remove(0);
            newXY.getCoord2().x = this.coord.getCoord2().x + p2.x;
            newXY.getCoord2().y = this.coord.getCoord2().y + p2.y;


            Point p3 = newPoints.remove(0);
            newXY.getCoord3().x = this.coord.getCoord3().x + p3.x;
            newXY.getCoord3().y = this.coord.getCoord3().y + p3.y;

            Point p4 = newPoints.remove(0);
            newXY.getCoord4().x = this.coord.getCoord4().x + p4.x;
            newXY.getCoord4().y = this.coord.getCoord4().y + p4.y;

            if (checkCollision(board, newXY)) {
                return false;
            }

            if (this.tetromino == this.RED_BLOCK) { //Pieza I
                for (int row = 0; row < this.I_LENGTH; row++) {
                    for (int col = 0; col < this.I_LENGTH; col++) {
                        this.pieceBoard[row][col] = auxI[row][col];
                    }
                }
            } else {
                for (int row = 0; row < this.LENGTH; row++) {
                    for (int col = 0; col < this.LENGTH; col++) {
                        this.pieceBoard[row][col] = aux[row][col];
                    }
                }
            }
            return true;
    }

    public boolean checkCollision(int [][] board, Coordinates newXY) {
        List<Point> newPoints = new ArrayList<>();
        newPoints.add(newXY.coord1);
        newPoints.add(newXY.coord2);
        newPoints.add(newXY.coord3);
        newPoints.add(newXY.coord4);

        int count = 0;

        for (Point point: newPoints) {
            if (this.coord.coord1.equals(point) || this.coord.coord2.equals(point) || this.coord.coord3.equals(point) || this.coord.coord4.equals(point)) {
                count++;
            }
            else if (point.x < board.length && point.x > -1 && point.y > -1 &&
                    board[0].length > point.y) {
                if (board[point.x][point.y] == NO_BLOCK) {
                    count++;
                }
            }
        }

        newPoints.clear();

        if (count == 4) {
            return false;
        }
        return true;
    }

    public Coordinates copyCoord(Coordinates coords) {
        Coordinates newXY = new Coordinates();

        newXY.coord1 = new Point(coords.coord1.x, coords.coord1.y);
        newXY.coord2 = new Point(coords.coord2.x, coords.coord2.y);
        newXY.coord3 = new Point(coords.coord3.x, coords.coord3.y);
        newXY.coord4 = new Point(coords.coord4.x, coords.coord4.y);

        return newXY;
    }

    /*
    Para realizar este método comprobamos recorriendo primero por columnas para actualizar por esas
    coordenadas primero, que sea del mismo tipo de pieza y que ademas con la ayuda de newXY que no sea una
    pieza del mismo tipo pero que no sea la pieza que nos interesa guardar.
     */

    public void updateAfterRotate(int [][] board, Coordinates newXY) {

        List<Integer> newPointsX = new LinkedList<>();
        List<Integer> newPointsY = new LinkedList<>();

        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row < board.length; row ++) {
                if (this.tetromino == board[row][col]) {
                    if (newXY.coord1.equals(row, col) || newXY.coord2.equals(row, col) || newXY.coord3.equals(row, col) || newXY.coord4.equals(row, col)) {
                        newPointsX.add(row);
                        newPointsY.add(col);
                    }
                }
            }
        }

        int x1 = newPointsX.remove(0);
        int y1 = newPointsY.remove(0);
        this.coord.getCoord1().x = x1;
        this.coord.getCoord1().y = y1;

        int x2 = newPointsX.remove(0);
        int y2 = newPointsY.remove(0);
        this.coord.getCoord2().x = x2;
        this.coord.getCoord2().y = y2;

        int x3 = newPointsX.remove(0);
        int y3 = newPointsY.remove(0);
        this.coord.getCoord3().x = x3;
        this.coord.getCoord3().y = y3;

        int x4 = newPointsX.remove(0);
        int y4 = newPointsY.remove(0);
        this.coord.getCoord4().x = x4;
        this.coord.getCoord4().y = y4;
    }
}
