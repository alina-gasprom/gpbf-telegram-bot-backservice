package ru.alina.gpbf.backservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.alina.gpbf.backservice.dto.ErrorResponseTo;

import java.util.UUID;


@ControllerAdvice
public class ControllerExceptionAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

    @ExceptionHandler(value = {BindException.class})
    public ResponseEntity<ErrorResponseTo> handleValidationException(BindException e) {
        LOG.warn(e.getMessage());
        ErrorResponseTo response = new ErrorResponseTo("Входящий json объект не соответствует требованиям спецификации", "Validation exception", "400", UUID.randomUUID());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public ResponseEntity<ErrorResponseTo> handleConflictException(DuplicateKeyException e) {
        LOG.warn(e.getMessage());
        ErrorResponseTo response = new ErrorResponseTo("Сущность с такими уникальными значениями уже существует", "Conflict exception", "409", UUID.randomUUID());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponseTo> handleUnexpectedException(Exception e) {
        LOG.warn(e.getMessage());
        ErrorResponseTo response = new ErrorResponseTo("Непредвиденная ошибка", "General error", "400", UUID.randomUUID());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
