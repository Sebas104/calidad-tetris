package com.example.protetris;

import android.graphics.Point;

import java.util.LinkedList;
import java.util.List;

public class IPiece extends Piece {

    private static final int I_LENGTH = 4;

    public IPiece() {
        this.pieceBoard = new int[I_LENGTH][I_LENGTH];
        for (int col = 0; col < I_LENGTH; col++) {
            for (int row = 0; row < I_LENGTH; row++) {
                this.pieceBoard[col][row] = NO_BLOCK;
            }
        }
        this.pieceBoard[2][0] = RED_BLOCK;
        this.pieceBoard[2][1] = RED_BLOCK;
        this.pieceBoard[2][2] = RED_BLOCK;
        this.pieceBoard[2][3] = RED_BLOCK;
    }

    @Override
    public boolean rotatePiece(int [][] board, Coordinates newXY) {

        List<Point> newPoints = new LinkedList<>();
        int aux[][] = new int[I_LENGTH][I_LENGTH];

        for (int col = 0; col < I_LENGTH; col++) { //Se recorre primero las columnas
            for (int row = 0; row < I_LENGTH; row++) {
                aux[I_LENGTH - 1 - col][row] = this.piece.pieceBoard[row][col];
                if (this.piece.pieceBoard[row][col] != NO_BLOCK) {
                    //Hacemos si row = 2 y col = 0 se lo restamos a
                    //las nuevas coordenadas que ha adquirido ese mismo bloque.
                    Point point = new Point(((I_LENGTH - 1 - col) - row), (row - col));
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

}