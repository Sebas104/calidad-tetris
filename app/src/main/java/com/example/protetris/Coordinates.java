package com.example.protetris;


import android.graphics.Point;

public class Coordinates {

    //Coordenadas de las piezas que se utilizan para el tablero
    protected Point coord1;
    protected Point coord2;
    protected Point coord3;
    protected Point coord4;

    public Coordinates() {
        this.coord1 = new Point();
        this.coord2 = new Point();
        this.coord3 = new Point();
        this.coord4 = new Point();
    }

    //Para mover la pieza por el tablero
    public void updateCoord(int newX, int newY) {
        this.coord1.x = this.coord1.x + newX;
        this.coord1.y = this.coord1.y + newY;
        this.coord2.x = this.coord2.x + newX;
        this.coord2.y = this.coord2.y + newY;
        this.coord3.x = this.coord3.x + newX;
        this.coord3.y = this.coord3.y + newY;
        this.coord4.x = this.coord4.x + newX;
        this.coord4.y = this.coord4.y + newY;
    }

    public boolean equals(Coordinates coord) {
        if (this.coord1.equals(coord.coord1) && this.coord2.equals(coord.coord2)
            && this.coord3.equals(coord.coord3) && this.coord4.equals(coord.coord4)) {
            return true;
        }
        return false;
    }

    public void setCoord1(int x, int y) {
        this.coord1.x = x;
        this.coord1.y = y;
    }

    public void setCoord2(int x, int y) {
        this.coord2.x = x;
        this.coord2.y = y;
    }

    public void setCoord3(int x, int y) {
        this.coord3.x = x;
        this.coord3.y = y;
    }

    public void setCoord4(int x, int y) {
        this.coord4.x = x;
        this.coord4.y = y;
    }

    public Point getCoord1() {
        return coord1;
    }

    public Point getCoord2() {
        return coord2;
    }

    public Point getCoord3() {
        return coord3;
    }

    public Point getCoord4() {
        return coord4;
    }
}
