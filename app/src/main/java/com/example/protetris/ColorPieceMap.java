package com.example.protetris;
import android.graphics.Color;

import java.util.HashMap;

public class ColorPieceMap {
    private HashMap<Integer,String> map;
    private String defaultColor = "#0030272A";
    public ColorPieceMap(){
        this.map = new HashMap<>();
        //Base color
        map.put(0,"0");
        //S piece
        map.put(1,"#0087FC");
        //I piece
        map.put(2,"#FD2929");
        //J piece
        map.put(3,"#00D1FE");
        //O piece
        map.put(4,"#9C00E2");
        //L piece
        map.put(5,"#FDD401");
        //Z piece
        map.put(6,"#FD6801");
        //T piece
        map.put(7,"#03DF04");
        //Shadow
        map.put(8,"#26F2F2F2");
    }

    public int pieceToColor(int piece){
        int aux;
        if (map.containsKey(piece)){
            aux = Color.parseColor(map.get(piece));

        }else{
            aux =  Color.parseColor(this.defaultColor);
        }
        return aux;
    }

    public String pieceToString(int piece) {
        if (map.containsKey(piece)){
            return map.get(piece);
        }else{
            return defaultColor;
        }
    }

}
