package com.example.protetris;



public class Score {

    private int actualScore;

    public void Score() {
        this.actualScore = 0;
    }

    //Clase creada para futuras implementaciones (Ej HighScore)


    public int getActualScore() {
        return this.actualScore;
    }

    public void setActualScore(int actualScore) {
        this.actualScore = actualScore;
    }
}
