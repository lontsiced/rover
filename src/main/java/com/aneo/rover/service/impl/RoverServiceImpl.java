package com.aneo.rover.service.impl;

import com.aneo.rover.model.Plateau;
import com.aneo.rover.model.Rover;
import com.aneo.rover.service.RoverService;
import org.springframework.stereotype.Service;

@Service
public class RoverServiceImpl implements RoverService {

    @Override
    public Rover landRover(String id, Plateau plateau, String dropInfo) {
        Rover rover = new Rover(id);
        rover.debarquer(plateau,  dropInfo);
        return rover;
    }
}
