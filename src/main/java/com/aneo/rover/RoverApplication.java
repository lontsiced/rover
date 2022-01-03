package com.aneo.rover;

import com.aneo.rover.exception.FileException;
import com.aneo.rover.exception.NoArgsException;
import com.aneo.rover.model.Instruction;
import com.aneo.rover.model.Plateau;
import com.aneo.rover.model.Rover;
import com.aneo.rover.service.PlateauService;
import com.aneo.rover.service.RoverService;
import com.aneo.rover.service.impl.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static java.lang.System.out;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RoverApplication  implements CommandLineRunner{
    @Autowired
    PlateauService plateauService;

    @Autowired
    RoverService roverService;

    public static void main(String[] args) {
        SpringApplication.run(RoverApplication.class, args);
    }

     @Override
    public void run(String... args) {
       List<String> results = new ArrayList<>();
        try {
            // chargement et lecture du fichier recu en ontion de commande lors de lexecution du programme
            List<String> data = FileUtils.readTextFromJarCommand(args[0]);
            //creation du Plateau a base de la premiere ligne
            Plateau plateau = plateauService.createPlateau(data.get(0));;
            for (int i = 2; i < data.size(); i=i+2){
              Rover rover = roverService.landRover(plateau,data.get(i-1));
              // application des instructions recu par le rover
              rover.appliquerInstructions(new Instruction(data.get(i)).lectureInstructions());
              // sauvegarde de letat final dans la liste des resultats
              results.add(rover.getEtat());
            }
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new NoArgsException();
        } catch (IllegalStateException e2) {
            throw new NoArgsException();
        }catch (Exception e3) {
            throw new FileException();

        }finally {
            for (String resul: results){
                out.println(resul);
            }
        }
    }
}
