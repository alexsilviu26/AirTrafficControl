package org.example;
import java.time.LocalTime;
import java.util.*;

public class Runway<T extends Airplane> {
    private String id;
    private String status = "FREE"; // FREE / BUSY
    private String use; // landing / takeoff
    private final TreeSet<T> scheduledAirplanes = new TreeSet<>();
    private LocalTime unavailableUntil = LocalTime.of(0, 0, 0);
    private String typeOfAircraft ;

    public String getTypeOfAircraft() {
        return typeOfAircraft;
    }

    public void setTypeOfAircraft(String typeOfAircraft) {
        this.typeOfAircraft = typeOfAircraft;
    }

    public LocalTime getUnavailableUntil() {
        return this.unavailableUntil;
    }

    public void setUnavailableUntil(LocalTime unavailableUntil) {
        this.unavailableUntil = unavailableUntil;
    }

    public TreeSet<T> getScheduledAirplanes() {
        return this.scheduledAirplanes;
    }

    public Runway() {}

    // Adauga un avion in lista de prioritati
    public void addAirplane(Airplane airplane, String timestamp) throws IncorrectRunwayException {
        if (airplane.getFlightStatus() != Airplane.FlightStatus.WAITING_FOR_TAKEOFF &&
                airplane.getFlightStatus() != Airplane.FlightStatus.WAITING_FOR_LANDING) {
            throw new IllegalArgumentException("Avionul trebuie sa fie in asteptare pentru decolare/aterizare.");
        }
        if(!airplane.getAirplaneType().equals(this.getTypeOfAircraft()))
            throw new IncorrectRunwayException(timestamp +" | The chosen runway for allocating the plane is incorrect");
        //Am luat decizia de SupressWarning pentru cast-ul de la Airplane la T deoarece
        //am verificat manual ca avionul poate fi castat la T si datorita faptului ca
        //in lipsa acestei linii de cod, compilatorul ar fi generat un warning de tipul
        //Unchecked cast
        @SuppressWarnings("unchecked")
        T airplaneT = (T) airplane;
        scheduledAirplanes.add(airplaneT);
    }

    // Returneaza o descriere a pistei
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(id + " - " + status + "\n");
        scheduledAirplanes.forEach(airplane -> stringBuilder.append(airplane).append("\n"));
        return stringBuilder.toString();
    }

    public void setId(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID-ul pistei nu poate fi null sau gol.");
        }
        this.id = id;
    }

    // Getter si setter pentru status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter si setter pentru utilizare
    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }
}
