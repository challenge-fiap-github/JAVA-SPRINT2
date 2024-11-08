package com.OdontoVison.Java.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Exceção para UsuarioNotFoundException
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> handleUsuarioNotFoundException(UsuarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Exceção para ConsultaNotFoundException
    @ExceptionHandler(ConsultaNotFoundException.class)
    public ResponseEntity<String> handleConsultaNotFoundException(ConsultaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Exceção para PontuacaoNotFoundException
    @ExceptionHandler(PontuacaoNotFoundException.class)
    public ResponseEntity<String> handlePontuacaoNotFoundException(PontuacaoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Exceção para RecompensaNotAvailableException
    @ExceptionHandler(RecompensaNotAvailableException.class)
    public ResponseEntity<String> handleRecompensaNotAvailableException(RecompensaNotAvailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Exceção para RecompensaNotFoundException
    @ExceptionHandler(RecompensaNotFoundException.class)
    public ResponseEntity<String> handleRecompensaNotFoundException(RecompensaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Exceção para EmailAlreadyInUseException
    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<String> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Exceção para InsufficientPointsException
    @ExceptionHandler(InsufficientPointsException.class)
    public ResponseEntity<String> handleInsufficientPointsException(InsufficientPointsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Exceção para RecompensaUnavailableException
    @ExceptionHandler(RecompensaUnavailableException.class)
    public ResponseEntity<String> handleRecompensaUnavailableException(RecompensaUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // Exceção para validação de dados com @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de validação: " + errors);
    }

    // Tratamento de exceções genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + ex.getMessage());
    }
}