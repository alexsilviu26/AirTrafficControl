package org.example;

import java.time.format.DateTimeFormatter;

public class WideBodyAirplane extends Airplane{
    // atributele specifice avioanelor wide body
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String realTimeStr;
    //suprascrierea metodei toString pentru a afisa informatiile despre avion
    @Override
    public String toString () {
        String expectedTimeStr;
        if(this.expectedTime != null)
            expectedTimeStr = getExpectedTime().format(formatter);
        else
            expectedTimeStr = "null";
        if (this.realTime == null)
            return  "Wide Body - " + model + " - "+ flightID + " - " + origin + " - " + destination + " - " + status + " - " + expectedTimeStr;
        else {
            realTimeStr = getRealTime().format(formatter);
            return "Wide Body - " + model + " - "+ flightID + " - " + origin + " - " + destination + " - " + status + " - " + expectedTimeStr + " - " + realTimeStr;
        }
    }
}
