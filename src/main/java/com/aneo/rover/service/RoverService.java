package com.aneo.rover.service;

import com.aneo.rover.model.Plateau;
import com.aneo.rover.model.Rover;

public interface RoverService {
    public Rover landRover(String id, Plateau plateau, String dropInfo);

}
