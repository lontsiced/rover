package com.aneo.rover.exception;

public class FileException extends RuntimeException{
    public FileException(){
        super("Le fichier passé en commande jar n'existe pas !");
    }
}
