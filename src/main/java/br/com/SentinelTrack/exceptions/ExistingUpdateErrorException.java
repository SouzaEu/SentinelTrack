package br.com.SentinelTrack.exceptions;

public class ExistingUpdateErrorException extends RuntimeException {
    public ExistingUpdateErrorException(String message) {
        super(message);
    }
}
