package br.com.api.core.exception;

public class AuthenticationModelNotFoundException extends RuntimeException {
    public AuthenticationModelNotFoundException() {
        super("authentication model doesn't exists");
    }
}
