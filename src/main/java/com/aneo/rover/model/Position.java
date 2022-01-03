package com.aneo.rover.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private int x;
    private int y;


    public boolean isEqual(Position position){
        return x == position.getX() && y == position.getY();
    }

    @Override
    public String toString(){
        return x+" "+y;
    }
}
