package com.example.protetris;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Piece extends Coordinates{

    protected static final int NO_BLOCK = 0;
    protected static final int BLUE_BLOCK = 1; //Piece S
    protected static final int RED_BLOCK = 2; //Piece I
    protected static final int PURPLE_BLOCK = 3; //Piece J
    protected static final int BLUELIGHT_BLOCK = 4; //Piece O
    protected static final int YELLOW_BLOCK = 5; //Piece L
    protected static final int ORANGE_BLOCK = 6; //Piece Z
    protected static final int GREEN_BLOCK = 7; //Piece T

    protected static final int LENGTH = 3; //Tamaño de los array de las piezas, 4 por la pieza I
    protected int [][] pieceBoard; //Array de la pieza para el método rotar
    protected Piece piece; //La propia pieza
    protected Coordinates coord = new Coordinates();
    private int tetromino; //El tipo de bloque

    public Piece() {
        this.pieceBoard = new int[LENGTH][LENGTH];
        for (int col = 0; col < LENGTH; col++) {
            for (int row = 0; row < LENGTH; row++) {
                this.pieceBoard[col][row] = NO_BLOCK;
            }
        }
    }

    //Al crear cada pieza se inicializan al principio del array tablero
    public Piece(int pieceType) {

        switch (pieceType) {
            case BLUE_BLOCK:
                this.piece = new SPiece();
                this.coord.setCoord1(1, 3);
                this.coord.setCoord2(0, 4);
                this.coord.setCoord3(1, 4);
                this.coord.setCoord4(0, 5);
                this.tetromino = BLUE_BLOCK;
                break;

            case RED_BLOCK:
                this.piece = new IPiece();
                this.coord.setCoord1(0, 3);
                this.coord.setCoord2(0, 4);
                this.coord.setCoord3(0, 5);
                this.coord.setCoord4(0, 6);
                this.tetromino = RED_BLOCK;
                break;

            case PURPLE_BLOCK:
                this.piece = new JPiece();
                this.coord.setCoord1(0, 3);
                this.coord.setCoord2(1, 3);
                this.coord.setCoord3(1, 4);
                this.coord.setCoord4(1, 5);
                this.tetromino = PURPLE_BLOCK;
                break;

            case BLUELIGHT_BLOCK:
                this.piece = new OPiece();
                this.coord.setCoord1(0, 4);
                this.coord.setCoord2(1, 4);
                this.coord.setCoord3(0, 5);
                this.coord.setCoord4(1, 5);
                this.tetromino = BLUELIGHT_BLOCK;
                break;

            case YELLOW_BLOCK:
                this.piece = new LPiece();
                this.coord.setCoord1(1, 3);
                this.coord.setCoord2(1, 4);
                this.coord.setCoord3(0, 5);
                this.coord.setCoord4(1, 5);
                this.tetromino = YELLOW_BLOCK;
                break;

            case ORANGE_BLOCK:
                this.piece = new ZPiece();
                this.coord.setCoord1(0, 3);
                this.coord.setCoord2(0, 4);
                this.coord.setCoord3(1, 4);
                this.coord.setCoord4(1, 5);
                this.tetromino = ORANGE_BLOCK;
                break;

            case GREEN_BLOCK:
                this.piece = new TPiece();
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
        return this.coord.getCoord3();
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

        for (int col = 0; col < LENGTH; col++) { //Se recorre primero las columnas
            for (int row = 0; row < LENGTH; row++) {
                aux[LENGTH - 1 - col][row] = this.piece.pieceBoard[row][col];
                if (this.piece.pieceBoard[row][col] != NO_BLOCK) {
                    //Hacemos si row = 2 y col = 0 se lo restamos a
                    //las nuevas coordenadas que ha adquirido ese mismo bloque.
                    Point point = new Point(((LENGTH - 1 - col) - row),(row - col));
                    newPoints.add(point);
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

        if  (checkCollision(board, newXY)) {
            return false;
        }

        this.piece.pieceBoard = aux; //Guardamos la rotación de la pieza
        return true;
    }



    /*
    A la hora de comprobar si la pieza puede actualizar su posicion, con el tablero de comprobacion
    sin la pieza y con las coordenadas anteriores almacendas y actualizadas sus coordenadas
    podemos ver si no hay colision tanto con otras piezas o se salga del tablero.
     */
    public boolean checkCollision(int [][] board, Coordinates newXY) {
        List<Point> newPoints = new ArrayList<>();
        newPoints.add(newXY.coord1);
        newPoints.add(newXY.coord2);
        newPoints.add(newXY.coord3);
        newPoints.add(newXY.coord4);

        int count = 0;

        for (Point point: newPoints) {
            if (this.coord.coord1.equals(point) || this.coord.equals(point) || this.coord.equals(point)) {
                count++;
            }
            else if (point.x < board.length && point.y > -1 &&
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

    public void updateAfterRotate(Coordinates newXY) {
        this.coord.setCoord1(newXY.getCoord1().x, newXY.getCoord1().y);
        this.coord.setCoord2(newXY.getCoord2().x, newXY.getCoord2().y);
        this.coord.setCoord3(newXY.getCoord3().x, newXY.getCoord3().y);
        this.coord.setCoord4(newXY.getCoord4().x, newXY.getCoord4().y);
    }
}
