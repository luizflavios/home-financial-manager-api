package br.com.api.core.exception;

public class RecursivePaymentNeedInstallmentException extends RuntimeException {

    public RecursivePaymentNeedInstallmentException(String message) {
        super(message);
    }

}
