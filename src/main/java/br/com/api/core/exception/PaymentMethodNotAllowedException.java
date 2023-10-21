package br.com.api.core.exception;

public class PaymentMethodNotAllowedException extends RuntimeException {

    public PaymentMethodNotAllowedException(String message) {
        super(message);
    }

}
