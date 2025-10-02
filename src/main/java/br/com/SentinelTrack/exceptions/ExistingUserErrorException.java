package br.com.SentinelTrack.exceptions;

import jakarta.validation.constraints.NotNull;

public class ExistingUserErrorException extends RuntimeException {
    public ExistingUserErrorException(String message) {
        super(message);
    }
}
