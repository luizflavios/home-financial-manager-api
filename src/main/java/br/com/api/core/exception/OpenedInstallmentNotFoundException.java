package br.com.api.core.exception;

public class OpenedInstallmentNotFoundException extends RuntimeException {

    public OpenedInstallmentNotFoundException(String message) {
        super(message);
    }

    public OpenedInstallmentNotFoundException() {
        super("ref transaction dont have opened installments");
    }

}
