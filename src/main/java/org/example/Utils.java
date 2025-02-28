package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    //metoda care parseaza in comezi liniiie din fisierul de input
    public  static void  Parse(String inputFilePath, ArrayList<String> comenzi) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line; // Linia curenta citita
            while ((line = reader.readLine()) != null) {
                comenzi.add(line);
            }
        } catch (IOException exception) {
            // Gestionam erorile de citire
            System.err.println("Eroare la citirea fisierului: " + exception.getMessage());
        }
    }
}
