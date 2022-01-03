package com.aneo.rover;

import com.aneo.rover.exception.FileException;
import com.aneo.rover.exception.NoArgsException;
import com.aneo.rover.service.impl.FileUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static java.lang.System.out;


import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
public class RoverApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RoverApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            List<String> data = FileUtils.readTextFromJarCommand(args[0]);
            for (String line: data){
                out.println(line);
            }
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new NoArgsException();
        } catch (IllegalStateException e2) {
            throw new NoArgsException();
        }catch (Exception e3) {
            throw new FileException();
        }
    }
}
