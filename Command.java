package org.example;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.HashMap;

public class Command {
    //metoda care adauga o pista in maparea pistelor
    public void addRunwayInUse(String[] commandArguments, HashMap<String, Runway<? extends Airplane>> runways) {
        //prelucram argumentele comenzii
        String id = commandArguments[2];
        String utilizationType = commandArguments[3];
        if(commandArguments[4].equals("narrow body"))
            commandArguments[4] = "Narrow Body";
        if(commandArguments[4].equals("wide body"))
            commandArguments[4] = "Wide Body";
        String typeOfAircraft = commandArguments[4];
        // Validam valorile
        if (!"landing".equals(utilizationType) && !"takeoff".equals(utilizationType)) {
            throw new IllegalArgumentException("Tipul de utilizare este invalid: " + utilizationType);
        }
        if (!"Narrow Body".equals(typeOfAircraft) && !"Wide Body".equals(typeOfAircraft)) {
            throw new IllegalArgumentException("Tipul de avion acceptat este invalid: " + typeOfAircraft);
        }

        // Cream pista in functie de tipul de avion acceptat
        if(typeOfAircraft.equals("Narrow Body")) {
            Runway<NarrowBodyAirplane> auxiiliarRunway = new Runway<>();
            auxiiliarRunway.setId(id);
            auxiiliarRunway.setStatus("FREE");
            auxiiliarRunway.setUse(utilizationType);
            auxiiliarRunway.setTypeOfAircraft(typeOfAircraft);
            // Adaugam pista in mapare
            runways.put(id, auxiiliarRunway);
        }
        if(typeOfAircraft.equals("Wide Body")) {
            Runway<WideBodyAirplane> auxiiliarRunway = new Runway<>();
            auxiiliarRunway.setId(id);
            auxiiliarRunway.setStatus("FREE");
            auxiiliarRunway.setUse(utilizationType);
            auxiiliarRunway.setTypeOfAircraft(typeOfAircraft);
            // Adaugam pista in mapare
            runways.put(id, auxiiliarRunway);
        }
    }

    //metoda care aloca un avion pe o pista
    public void allocatePlane(String[] commandArguments, HashMap<String, Runway<? extends Airplane>> runways, HashMap<String, Airplane> airplanes) throws IncorrectRunwayException {
        //initializam variabilele auxiliare necesare
        Runway<? extends Airplane> currentRunway = runways.get(commandArguments[8]);
        String use = currentRunway.getUse();
        //in functie de tipul de avion, adaugam avionul
        if(commandArguments[2].equals("narrow body")) {
            commandArguments[2] = "Narrow Body";
            NarrowBodyAirplane airplane = new NarrowBodyAirplane();
            airplane.setAirplane(airplane, commandArguments, runways, use);
            airplanes.put(commandArguments[4], airplane);
        }
        if(commandArguments[2].equals("wide body")) {
            commandArguments[2] = "Wide Body";
            WideBodyAirplane airplane = new WideBodyAirplane();
            airplane.setAirplane(airplane, commandArguments, runways, use);
            airplanes.put(commandArguments[4], airplane);
        }
    }

    //metoda care acorda permisiunea pentru manevra pe pista
    public void permissionForManeuver(String[] commandArguments, HashMap<String, Runway<? extends Airplane>> runways) throws UnavailableRunwayException {
        //initializam variabilele auxiliare necesare
        Runway<? extends Airplane> currentRunway = runways.get(commandArguments[2]);
        Airplane currentAirplane = currentRunway.getScheduledAirplanes().first();
        LocalTime timestamp = LocalTime.parse(commandArguments[0]);
        //prelucram timestamp-ul pentru a-l afisa in formatul dorit
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTimestamp = LocalTime.parse(commandArguments[0]).format(formatter);
        //verificam daca pista este ocupata si daca este ocupata lansam o exceptie
        LocalTime unavaible = currentRunway.getUnavailableUntil();
        currentRunway.setStatus("OCCUPIED");
        if(unavaible.isBefore(timestamp)) {
            currentRunway.setStatus("FREE");
            runways.get(commandArguments[2]).setStatus("FREE");
        }
        //daca pista este ocupata, lansam o exceptie
        if(currentRunway.getStatus().equals("OCCUPIED")) {
            throw new UnavailableRunwayException(formattedTimestamp + " | The chosen runway for maneuver is currently occupied");
        }
        //daca timestampul pistei care ne spune pana cand este indisponibila este mai mic decat timestampul curent
        //atunci putem acorda permisiunea pentru manevra
        if(unavaible.isBefore(timestamp)) {
            //facem prelucrarea in functie de tipul de utilizare a pistei
            if (currentRunway.getUse().equals("landing")) {
                currentAirplane.setStatus(Airplane.FlightStatus.LANDED);
                currentAirplane.setRealTime(timestamp);
                currentRunway.setUnavailableUntil(timestamp.plusMinutes(10));
            }
            if (currentRunway.getUse().equals("takeoff")) {
                currentAirplane.setStatus(Airplane.FlightStatus.DEPARTED);
                currentAirplane.setRealTime(timestamp);
                currentRunway.setUnavailableUntil(timestamp.plusMinutes(5));
            }
            //eliminam avionul din lista de avioane a pistei deoarece si-a executat manevra
            currentRunway.getScheduledAirplanes().remove(currentAirplane);
        }
    }

    //metoda care afiseaza informatiile despre o pista
    public void runwayInfo(String[] commandArguments, String path, HashMap<String, Runway<? extends Airplane>> runways) throws FileNotFoundException {
        //formatam timestampul pentru a-l adauga in numele fisierului de output
        String formattedTimestamp = LocalTime.parse(commandArguments[0]).toString().replace(":", "-");
        String outputFilePath = path + "/runway_info_" + commandArguments[2] + "_" + formattedTimestamp + "-00.out";

        // Crearea directoarelor, daca este necesar
        File file = new File(outputFilePath);
        Runway<? extends Airplane> currentRunway = runways.get(commandArguments[2]);
        if(currentRunway.getUnavailableUntil().isBefore(LocalTime.parse(commandArguments[0])))
            currentRunway.setStatus("FREE");
        if(currentRunway.getUnavailableUntil().isAfter(LocalTime.parse(commandArguments[0])))
            currentRunway.setStatus("OCCUPIED");
        //scriem in fisier informatiile despre pista
        PrintWriter writer = new PrintWriter(file);
        writer.print(runways.get(commandArguments[2]));
        writer.close();
    }

    //metoda care afiseaza informatiile despre zborul unui avion
    public void flightInfo(String[] commandArguments, String path, HashMap<String, Airplane> airplanes) throws IOException {
        //initializam fisierul de output
        String outputFilePath = path + "/flight_info.out";
        File file = new File(outputFilePath);

        // Inceram sa scriem in fisier
        try (FileWriter fw = new FileWriter(file, true); PrintWriter writer = new PrintWriter(fw)) {
            //alocam variabile auxiliare necesare
            Airplane currentAirplane = airplanes.get(commandArguments[2]);
            //prelucram timestamp-ul pentru a-l afisa in formatul dorit
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timestamp = LocalTime.parse(commandArguments[0]).format(formatter);
            String expectedTime = currentAirplane.getExpectedTime() != null ? currentAirplane.getExpectedTime().format(formatter) : "null";
            String realTime  = currentAirplane.getRealTime() != null ? currentAirplane.getRealTime().format(formatter) : "null";
            // scriem in fisier informatiile despre zborul avionului
            if(currentAirplane.getRealTime() != null)
                writer.println(timestamp + " | " + currentAirplane.getAirplaneType() + " - " + currentAirplane.getModel() + " - " +
                               currentAirplane.getFlightID() + " - " + currentAirplane.getOrigin() + " - " + currentAirplane.getDestination()
                               + " - " + currentAirplane.getStatus() + " - " + expectedTime + " - " + realTime);
            else
                writer.println(timestamp + " | " + currentAirplane.getAirplaneType() + " - " + currentAirplane.getModel() + " - " +
                               currentAirplane.getFlightID() + " - " + currentAirplane.getOrigin() + " - " + currentAirplane.getDestination()
                               + " - " + currentAirplane.getStatus() + " - " + expectedTime);
        }
    }
}
