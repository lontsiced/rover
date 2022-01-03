package com.aneo.rover;

import com.aneo.rover.enu.Actions;
import com.aneo.rover.exception.NotPositionException;
import com.aneo.rover.model.Instruction;
import com.aneo.rover.model.Plateau;
import com.aneo.rover.model.Rover;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoverApplicationTests {
    private Plateau plateau = new Plateau(5, 5);

    @Test
    void contextLoads() {
    }


    @Test
    public void land_rover_succes() {
        Rover rover = new Rover();
        rover.debarquer(plateau, 1, 2, 'N');
        rover.appliquerInstructions(convertToArray("LMLMLMLMM"));;
        String etatFinal = rover.getStringPosition();
        assertEquals("1 3 N", etatFinal);
    }
    @Test
    public void land_rover_throw() {
        Rover rover = new Rover();
        rover.debarquer(plateau, 2, 2, 'N');

        try {
            rover.appliquerInstructions(convertToArray("MMMMMMMM"));
            assertTrue("Le debarquement de ce rover n'est pas possible", false);
        }
        catch (NotPositionException ex) {
            assertEquals("Position not exist on plateau!", ex.getMessage());
        }
    }


    private static Actions[] convertToArray(String instructions) {
        return new Instruction(instructions).lectureInstructions();
    }

}
