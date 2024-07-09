package ru.alina.gpbf.backservice;

import ru.alina.gpbf.backservice.dto.ErrorResponseTo;

import java.util.UUID;

public class ErrorData {
    public static final ErrorResponseTo ERROR_VALIDATION_RESPONSE = new ErrorResponseTo("Входящий json объект не соответствует требованиям спецификации", "Validation exception", "400", UUID.randomUUID());
    public static final ErrorResponseTo ERROR_CONFLICT_RESPONSE = new ErrorResponseTo("Сущность с такими уникальными значениями уже существует", "Conflict exception", "409", UUID.randomUUID());
}
