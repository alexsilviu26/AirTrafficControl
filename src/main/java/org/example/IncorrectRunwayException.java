package org.example;
// clasa care defineste exceptia pentru cazul in care pista este incorecta
public class IncorrectRunwayException extends Throwable {
    public IncorrectRunwayException(String message) {
        super(message);
    }
}
