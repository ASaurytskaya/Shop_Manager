package by.fin.shop_manager.service;

import by.fin.shop_manager.exception.BadRequestException;

import by.fin.shop_manager.exception.CannotReadImageFileException;
import by.fin.shop_manager.exception.ImageFileNotFoundException;
import by.fin.shop_manager.exception.ProductNotFoundException;
import by.fin.shop_manager.util.ErrorResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    private static final String ERROR_TYPE = "error";

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotReadImageFileException.class)
    public ResponseEntity<ErrorResponse> handleCannotReadImageFileException(CannotReadImageFileException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageFileNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleImageFileNotFoundException(ImageFileNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(ERROR_TYPE, "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
