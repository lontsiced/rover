package com.aneo.rover.model;

import com.aneo.rover.enu.Actions;
import com.aneo.rover.enu.Orientation;
import com.aneo.rover.exception.NotPositionException;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rover {

    private String nom;
    private Position position;
    private Orientation orientation;
    private Plateau plateau;

    public Rover(String nom){
        this.nom = nom;
    }

    public void debarquer(Plateau plateau, String args) {
        String[] parts = args.split(" ");
        int x = Integer.parseInt(parts[0]);// Character.getNumericValue(parts[0].toCharArray()[0]);
        int y = Integer.parseInt(parts[1]);// Character.getNumericValue(parts[1].toCharArray()[0]);
        Orientation orientation = toHeading(parts[2].toCharArray()[0]);
        debarquer(plateau, new Position(x, y), orientation);
    }

    public void debarquer(Plateau plateau, Position position, Orientation orientation) {
        if (!plateau.positionExiste(position)) {
            throw new NotPositionException(plateau, position);
        }

        if (!plateau.emplacementLibre(position)) {
            throw new RuntimeException("Emplacement deja occupé par un rover!");
        }

        this.plateau = plateau;
        this.position = position;
        this.orientation = orientation;

        plateau.debarquerRover(this);
    }

    public void debarquer(Plateau plateau, int posX, int posY, char orientation) {
        debarquer(plateau, new Position(posX, posY), toHeading(orientation));
    }

    public void appliquerInstructions(Actions[] actions){
        for (Actions action: actions){
            appliquerInstruction(action);
        }
    }

    private void appliquerInstruction(Actions action){
        if(position == null || orientation == null){
            throw new RuntimeException("Position ou orientation manquante !");
        }

        switch (action){
            case L: aGauche();break;
            case R: aDroite();break;
            case M: avancer();break;
            default: throw new RuntimeException("Mauvaise instruction!");
        }
    }

    public String getEtat() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStringPosition());
        return sb.toString();
    }

    public String getStringPosition() {
        if (position == null || orientation == null) {
            return "Not dropped yet.";
        }
        return position + " " + fromHeading(orientation);
    }


    private void aGauche() {
        switch (orientation) {
            case E: orientation = orientation.N; break;
            case N: orientation = orientation.O; break;
            case S: orientation = orientation.E; break;
            case O: orientation = orientation.S; break;
            default: throw new RuntimeException("Ne devrait pas arriver ici!");
        }
    }

    private void aDroite() {
        switch (orientation) {
            case E: orientation = orientation.S; break;
            case N: orientation = orientation.E; break;
            case S: orientation = orientation.O; break;
            case O: orientation = orientation.N; break;
            default: throw new RuntimeException("Ne devrait pas arriver ici!");
        }
    }

    private void avancer() {
        Position newPosition = faireMouvement();
        if (!plateau.positionExiste(newPosition)) {
            throw new NotPositionException(plateau, newPosition);
        }
        position = newPosition;
    }

    private Position faireMouvement(){
        switch (orientation){
            case E: return new Position(position.getX() + 1,position.getY());
            case O: return new Position(position.getX() - 1,position.getY());
            case N: return new Position(position.getX(),position.getY() + 1);
            case S: return new Position(position.getX(),position.getY() - 1);
            default: throw new RuntimeException("Orientation incorrect");
        }
    }

    private Orientation toHeading(char orientation) {
        switch (orientation) {
            case 'N': return Orientation.N;
            case 'W': return Orientation.O;
            case 'S': return Orientation.S;
            case 'E': return Orientation.E;
            default: throw new RuntimeException("Unsupported character '" + orientation + "'!");
        }
    }

    private char fromHeading(Orientation orientation) {
        switch (orientation) {
            case N: return 'N';
            case O: return 'O';
            case S: return 'S';
            case E: return 'E';
            default: throw new RuntimeException("Unsupported heading '" + orientation + "'!");
        }
    }
}
