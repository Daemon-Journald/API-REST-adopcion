package com.apis.adopcionmascota.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> manejarBadRequestException(BadRequestException ex) {
        ModeloError modeloError = new ModeloError();
        modeloError.setEstado(HttpStatus.BAD_REQUEST);
        modeloError.setFecha(new Date());
        modeloError.setMensaje(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modeloError);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> manejarPersonaNotFoundException(NotFoundException ex) {
        ModeloError modeloError = new ModeloError();
        modeloError.setEstado(HttpStatus.NOT_FOUND);
        modeloError.setFecha(new Date());
        modeloError.setMensaje(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(modeloError);
    }
    @ExceptionHandler(PersonaAlreadyExistsExeption.class)
    public ResponseEntity<?> manejarPersonaExistException(PersonaAlreadyExistsExeption ex) {
        ModeloError modeloError = new ModeloError();
        modeloError.setEstado(HttpStatus.BAD_REQUEST);
        modeloError.setFecha(new Date());
        modeloError.setMensaje(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modeloError);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ModeloError modeloError=new ModeloError();
        modeloError.setMensaje(ex.getMessage());
        modeloError.setEstado(status);
        modeloError.setFecha(new Date());

        return ResponseEntity.status(status).headers(headers).body(modeloError);

    }
}
