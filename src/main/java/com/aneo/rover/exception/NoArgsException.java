package com.aneo.rover.exception;

public class NoArgsException  extends RuntimeException{
    public NoArgsException(){
        super("Commande passée au programme incorrecte !");
    }
}
