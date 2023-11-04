package br.com.api.core.exception.handler;

import br.com.api.core.exception.OpenedInstallmentNotFoundException;
import br.com.api.core.exception.PaymentMethodNotAllowedException;
import br.com.api.core.exception.model.Error;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.time.LocalDateTime.now;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var error = Error.builder()
                .cause(statusCode.toString())
                .message(ex.getCause().getCause().getMessage())
                .statusCode(statusCode.value())
                .timestamp(now())
                .build();

        return new ResponseEntity<>(error, statusCode);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var body = Error.builder()
                .cause(status.toString())
                .message(ex.getMessage())
                .statusCode(status.value())
                .timestamp(now())
                .build();

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException() {
        var status = HttpStatus.NOT_FOUND;

        var body = Error.builder()
                .cause(status.getReasonPhrase())
                .message("Entity not found")
                .statusCode(status.value())
                .timestamp(now())
                .build();

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(PaymentMethodNotAllowedException.class)
    public ResponseEntity<Object> handlePaymentMethodNotAllowedException(PaymentMethodNotAllowedException exception) {
        var status = HttpStatus.PRECONDITION_FAILED;

        var body = Error.builder()
                .cause(status.getReasonPhrase())
                .message(exception.getMessage())
                .statusCode(status.value())
                .timestamp(now())
                .build();

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(OpenedInstallmentNotFoundException.class)
    public ResponseEntity<Object> handleOpenedInstallmentNotFoundException(OpenedInstallmentNotFoundException exception) {
        var status = HttpStatus.PRECONDITION_FAILED;

        var body = Error.builder()
                .cause(status.getReasonPhrase())
                .message(exception.getMessage())
                .statusCode(status.value())
                .timestamp(now())
                .build();

        return new ResponseEntity<>(body, status);
    }


    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Object> handleUnsupportedOperationException(UnsupportedOperationException exception) {
        var status = HttpStatus.NOT_ACCEPTABLE;

        var body = Error.builder()
                .cause(status.getReasonPhrase())
                .message(exception.getMessage())
                .statusCode(status.value())
                .timestamp(now())
                .build();

        return new ResponseEntity<>(body, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var body = Error.builder()
                .cause(status.toString())
                .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .statusCode(status.value())
                .timestamp(now())
                .build();

        return new ResponseEntity<>(body, status);
    }

}
