package br.com.SentinelTrack.exceptions;

public class UpdateNotFoundException extends RuntimeException {
    public UpdateNotFoundException(String message) {
        super(message);
    }
}
