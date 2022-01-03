package com.aneo.rover.exception;

public class NotLandedException extends RuntimeException {
    public NotLandedException(){
        super("Rover was not landed on plateau !");
    }
}
