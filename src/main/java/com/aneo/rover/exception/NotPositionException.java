package com.aneo.rover.exception;

import com.aneo.rover.model.Plateau;
import com.aneo.rover.model.Position;

public class NotPositionException extends RuntimeException{

    private Plateau plateau;
    private Position position;

    public NotPositionException(Plateau plateau, Position position){
        super("Unknown position ("+position.getX()+","+position.getY()+") on this plateau!");
        this.plateau = plateau;
        this.position = position;
    }
    public Plateau getPlateau() {
        return plateau;
    }

    public Position getPosition() {
        return position;
    }
}
