package org.example;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;


public class Airplane implements Comparable<Airplane> {
    // atributele comune ale avioanelor
    protected String model;
    protected String airplaneType;
    protected String flightID;
    protected String origin;
    protected String destination;
    protected LocalTime expectedTime;
    protected LocalTime realTime;
    protected FlightStatus status;
    protected boolean isEmergency = false;
    protected String waitingFor;

    //suprascrierea metodei compareTo pentru a sorta avioanele corespunzator cerintei
    @Override
    public int compareTo(Airplane airplane) {
        if(this.getEmergency() && !airplane.getEmergency()) {
            return -1;
        } else if(!this.getEmergency() && airplane.getEmergency()) {
            return 1;
        } else if (this.expectedTime.isBefore(airplane.expectedTime)) {
            return -1;
        } else if (this.expectedTime.isAfter(airplane.expectedTime)) {
            return 1;
        } else {
            return 0;
        }
    }

    //gettere si setere pentru atributele avioanelor
    //unele metode de set returneaza obiectul pentru a putea fi folosite in cascada
    public String getStatus() {
        return status.toString();
    }

    public LocalTime getRealTime() {
        return realTime;
    }

    public LocalTime getExpectedTime() {
        return expectedTime;
    }

    public String getFlightID() {
        return flightID;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getModel() {
        return model;
    }

    public Airplane setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
        return this;
    }

    public void setEmergency(boolean emergency) {
        this.isEmergency = emergency;
    }

    public Airplane setModel(String model) {
        this.model = model;
        return this;
    }

    public Airplane setFlightID(String flightID) {
        this.flightID = flightID;
        return this;
    }

    public Airplane setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public Airplane setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public void setExpectedTime(LocalTime expectedTime) {
        this.expectedTime = expectedTime;
    }

    public void setRealTime(LocalTime realTime) {
        this.realTime = realTime;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public boolean getEmergency() {
        return isEmergency;
    }

    //enumerare pentru statusul avioanelor
    public enum FlightStatus {WAITING_FOR_TAKEOFF, DEPARTED, WAITING_FOR_LANDING, LANDED}

    //constructor pentru avion
    public Airplane ( ) {
        this.model = null;
        this.flightID = null;
        this.origin = null;
        this.destination = null;
        this.expectedTime = null;
        this.realTime = null;
        this.status = null;
    }

    public FlightStatus getFlightStatus () {
        return this.status;
    }

    //metoda ce adauga un avion dat ca parametru in lista de avioane a pistei
    public void setAirplane(Airplane airplane, String[] commandParts, HashMap<String, Runway<? extends Airplane>> runways, String use) throws IncorrectRunwayException{
        //daca avem si un al 10-lea argument, inseamna ca avionul este de urgenta
        if (commandParts.length > 9 && use.equals("landing")) {
            airplane.setEmergency(true);
        }
        //setam atributele avionului ce urmeaza a fi adaugat in lista de avioane a pistei
        airplane.setAirplaneType(commandParts[2]).setModel(commandParts[3]).setFlightID(commandParts[4]).setOrigin(commandParts[5]).setDestination(commandParts[6])
                .setExpectedTime(LocalTime.parse(commandParts[7]));
        //prelucram timestamp-ul pentru a-l afisa in formatul dorit
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timestamp = LocalTime.parse(commandParts[0]).format(formatter);
        //in funtie de destinatie si origine, setam statusul avionului
        if(airplane.getDestination().equals("Bucharest")){
            airplane.setStatus(FlightStatus.WAITING_FOR_LANDING);
            airplane.waitingFor = "landing";
        }
        if(airplane.getOrigin().equals("Bucharest")) {
            airplane.setStatus(FlightStatus.WAITING_FOR_TAKEOFF);
            airplane.waitingFor = "takeoff";
        }
        //daca tipul de avion nu corespunde cu tipul pistei, aruncam o exceptie
        if(!runways.get(commandParts[8]).getUse().equals(airplane.waitingFor))
            throw new IncorrectRunwayException(timestamp +" | The chosen runway for allocating the plane is incorrect");
        //adaugam avionul in lista de avioane a pistei
        runways.get(commandParts[8]).addAirplane(airplane, timestamp);
    }
}
