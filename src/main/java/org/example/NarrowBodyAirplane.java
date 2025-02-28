package org.example;

import java.time.format.DateTimeFormatter;

public class NarrowBodyAirplane extends Airplane {
    // atributele specifice avioanelor narrow body
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String expectedTimeStr;
    String realTimeStr;
    //suprascrierea metodei toString pentru a afisa informatiile despre avion
    @Override
    public String toString () {
        if(this.expectedTime != null)
            expectedTimeStr = getExpectedTime().format(formatter);
        else
            expectedTimeStr = "null";
        if (this.realTime == null)
            return  "Narrow Body - " + model + " - "+ flightID + " - " + origin + " - " + destination + " - " + status + " - " + expectedTimeStr;
        else {
            realTimeStr = getRealTime().format(formatter);
            return "Narrow Body - " + model + " - "+ flightID + " - " + origin + " - " + destination + " - " + status + " - " + expectedTimeStr + " - " + realTimeStr;
        }
    }
}
