package br.com.SentinelTrack.exceptions;

public class ExistingPlateErrorException extends RuntimeException{

    public ExistingPlateErrorException(String message) {
        super(message);
    }
}
