package com.parking.parkingspaces.config.exception;

import com.parking.parkingspaces.adapters.controller.dto.Response;
import com.parking.parkingspaces.config.utility.Constants;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ParkingSpaceCustomErrorException.class)
    public ResponseEntity<Response> handleParkingCustomErrorException(
            ParkingSpaceCustomErrorException ex, WebRequest request) {

        Response response = new Response(ex.getMessage());
        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        response.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParkingSpaceNotFoundException.class)
    public ResponseEntity<Response> handleParkingNotFoundException(
            ParkingSpaceNotFoundException ex, WebRequest request) {

        Response response = new Response(ex.getMessage());
        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        response.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Response> handleDataNotFoundException(
            DataNotFoundException ex, WebRequest request) {

        Response response = new Response(Constants.DATA_NOT_FOUND_EXCEPTION);
        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        response.setStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParkingCreationLimitException.class)
    public ResponseEntity<Response> handleParkingCreationLimitException(
            ParkingCreationLimitException ex, WebRequest request) {

        Response response = new Response(ex.getMessage());
        response.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        response.setStatus(HttpStatus.CONFLICT);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Response> handleConflict(
            RuntimeException ex, WebRequest request) {
        Response response = Response.builder()
                .message(Constants.INTERNAL_SERVER_ERROR)
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        Response response = new Response(HttpStatus.BAD_REQUEST,
                Constants.FIELDS_REQUIRED,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")),
                errorList);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
