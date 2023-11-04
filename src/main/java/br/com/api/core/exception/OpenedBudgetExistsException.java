package br.com.api.core.exception;

public class OpenedBudgetExistsException extends RuntimeException {

    public OpenedBudgetExistsException(String message) {
        super(message);
    }

    public OpenedBudgetExistsException() {
        super("ref family already have opened and active budget");
    }

}
