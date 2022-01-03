package com.aneo.rover.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readTextFromJarCommand(String s) {
        List<String> list = new ArrayList<>();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            // Le fichier d'entrée
            File file = new File(s);
            // Créer l'objet File Reader
            fr = new FileReader(file);
            // Créer l'objet BufferedReader
            br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)
            {
                // ajoute la ligne a la liste
                list.add(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}
