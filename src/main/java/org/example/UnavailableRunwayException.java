package org.example;
// clasa care defineste exceptia pentru cazul in care pista este indisponibila
public class UnavailableRunwayException extends Exception {
    public UnavailableRunwayException(String message) {
        super(message);
    }
}