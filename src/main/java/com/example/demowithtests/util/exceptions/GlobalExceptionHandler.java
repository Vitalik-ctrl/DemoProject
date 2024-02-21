package com.example.demowithtests.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        //ErrorDetails errorDetails = new ErrorDetails(new Date(), "Author not found with id =" + request.getParameter("id"), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceWasDeletedException.class)
    protected ResponseEntity<MyGlobalExceptionHandler> handleDeleteException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(new Date(), "This user was deleted"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmailDuplicatedException.class)
    public ResponseEntity<?> emailDuplicateException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(new Date(), "Email already exist in database"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NameIsEmptyException.class)
    public ResponseEntity<?> nameIsEmptyException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(new Date(), "Name is empty"), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DataIsEmptyException.class)
    public ResponseEntity<?> dataIsEmptyException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(new Date(), "There are no any data in database"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeWasAlreadyDeleted.class)
    public ResponseEntity<?> employeeWasAlreadyDeleted() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(new Date(), "Employee was already deleted"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<?> idNotFoundException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(new Date(), "Data with this ID is not exist"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OldValuesException.class)
    public ResponseEntity<?> oldValuesException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(new Date(), "There are no changes in this update"), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(UnhandledException.class)
    public ResponseEntity<?> unhandledException() {
        return new ResponseEntity<>(new MyGlobalExceptionHandler(new Date(), "Unhandled exception"), HttpStatus.EXPECTATION_FAILED);
    }

    @Data
    @AllArgsConstructor
    private static class MyGlobalExceptionHandler {
        private Date date;
        private String message;
    }
}
