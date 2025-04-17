package com.springboot.customerAndOrderManagementApplication.adviser;

import com.springboot.customerAndOrderManagementApplication.exception.NotFoundException;
import com.springboot.customerAndOrderManagementApplication.exception.ValidateException;
import com.springboot.customerAndOrderManagementApplication.util.StanderdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//400 - bad request
//404-not found
//500- internal serve error
@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<StanderdResponse> exceptionHandler(Exception exception) {

//        return new ResponseEntity<StanderdResponse>(
//                new StanderdResponse(500, "error", exception.getMessage()),
//                HttpStatus.INTERNAL_SERVER_ERROR);

//        ResponseEntity<StanderdResponse> response = new ResponseEntity<StanderdResponse>(
//                new StanderdResponse(500, "error", exception.getMessage()),
//               HttpStatus.INTERNAL_SERVER_ERROR
//        );
//
//        return response;

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new StanderdResponse(500, "error", exception.getMessage()));
    }


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<StanderdResponse> notFoundHandler(NotFoundException notFoundException) {
        return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(404, "error", notFoundException.getMessage()),
                HttpStatus.NOT_FOUND
        );

    }

    @ExceptionHandler(value = ValidateException.class)
    public ResponseEntity<StanderdResponse> validateHandler(ValidateException validateException) {

       return new ResponseEntity<StanderdResponse>(
                new StanderdResponse(400, "error", validateException.getMessage()),
                HttpStatus.BAD_REQUEST

        );

    }

}
