package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) throws IOException {
        //declaram obiectele necesare
        Command command = new Command();
        ArrayList<String> commands = new ArrayList<>();
        HashMap<String, Runway<? extends Airplane>> runways = new HashMap<>();
        HashMap<String, Airplane> airplanes = new HashMap<>();
        // prelucram fisierele de input/output
        String testName = args[0];
        String folderPath = "src/main/resources/" + testName;
        String inputFilePath = folderPath + "/input.in";
        String outputFilePath = folderPath + "/flight_info.out";
        String exceptionFilePath = folderPath + "/board_exceptions.out";
        File outputFile = new File(outputFilePath);
        File exceptionFile = new File(exceptionFilePath);
        //verificam daca fisierele de iesire existente si le stergem
        boolean deleted = false;
        if (outputFile.exists()) {
            deleted = outputFile.delete();
        }
        if (exceptionFile.exists()) {
            deleted = outputFile.delete();
        }
        if(deleted) {
            System.out.println("Fisierele de iesire existente au fost sterse.");
        }
        // citim comenzile din fisierul de input
        Utils.Parse(inputFilePath, commands);
        //parcurgem comenzile si le executam
        for (String currentCommand : commands) {
            //impartim comanda in argumente
            String[] commandArguments = currentCommand.split(" - ");
            String toDo = commandArguments[1];
            //in functie de comanda executam actiunea corespunzatoare
            switch (toDo) {
                case ("add_runway_in_use"):
                    command.addRunwayInUse(commandArguments, runways);
                    break;
                case ("allocate_plane"):
                    //gestionam posibilele exceptii aruncate de metoda
                    try {
                        command.allocatePlane(commandArguments, runways, airplanes);
                    } catch (IncorrectRunwayException exception) {
                        try (FileWriter fw = new FileWriter(exceptionFilePath, true); PrintWriter writer = new PrintWriter(fw)) {
                            writer.println(exception.getMessage());
                        }
                    }
                    break;
                case ("permission_for_maneuver"):
                    //gestionam posibilele exceptii aruncate de metoda
                    try {
                        command.permissionForManeuver(commandArguments, runways);
                    } catch (UnavailableRunwayException exception) {
                        try (FileWriter fw = new FileWriter(exceptionFilePath, true); PrintWriter writer = new PrintWriter(fw)) {
                            writer.println(exception.getMessage());
                        }
                    }
                    break;
                case ("runway_info"):
                    command.runwayInfo(commandArguments, folderPath, runways);
                    break;
                case ("flight_info"):
                    command.flightInfo(commandArguments, folderPath, airplanes);
                    break;
                case ("exit"):
                    return;
                default:
                    break;
            }
        }
    }
}