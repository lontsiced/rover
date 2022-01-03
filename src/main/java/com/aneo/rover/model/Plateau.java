package com.aneo.rover.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Plateau {
    private int dimensionX;
    private int dimensionY;
    private List<Rover> roverList = new ArrayList<Rover>();

    public Plateau(int x, int y){
        this.dimensionX = x;
        this.dimensionY = y;
    }

    public void debarquerRover(Rover rover){
        if(positionExiste(rover.getPosition()) && emplacementLibre(rover.getPosition())){
            roverList.add(rover);
        }else {
            throw new RuntimeException("Position ou emplacement incorrect !");
        }

    }

    public Boolean emplacementLibre(Position position){
        for (Rover rover: roverList){
            if(rover.getPosition().isEqual(position)){
                return false;
            }
        }
        return true;
    }

    public boolean positionExiste(Position position){
        if(position.getX() < 0 || position.getX()  > dimensionX){
            return false;
        }
        if(position.getY() < 0 || position.getY()  > dimensionY){
            return false;
        }
        return true;
    }
}
