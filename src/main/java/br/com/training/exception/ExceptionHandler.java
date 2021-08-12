package br.com.training.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<String> typeErrorList = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            typeErrorList.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            typeErrorList.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        MessageException apiError =
                new MessageException(HttpStatus.BAD_REQUEST, LocalDateTime.now(),
                        "Um ou mais campos precisam ser preenchidos corretamente.", typeErrorList);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }
}
