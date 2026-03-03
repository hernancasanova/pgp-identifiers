package com.identifiers.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.identifiers.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* =========================
       404 - Recurso no encontrado
       ========================= */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(RecursoNoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(404, ex.getMessage()));
    }

    /* =========================
       400 - Validaciones @Valid
       ========================= */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<Map<String, String>>>> handleValidation(
            MethodArgumentNotValidException ex) {

        List<Map<String, String>> errors = new ArrayList<>();

        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            Map<String, String> e = new HashMap<>();
            e.put("field", err.getField());
            e.put("message", err.getDefaultMessage());
            errors.add(e);
        }

        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(400, "Validation failed", errors));
    }

    /* =========================
       400 - JSON mal formado
       ========================= */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadJson(HttpMessageNotReadableException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(400, "Malformed JSON request"));
    }

    /* =========================
       403 - Acceso denegado
       ========================= */
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse<Void>> handleAccessDenied(AccessDeniedException ex) {
//        return ResponseEntity
//                .status(HttpStatus.FORBIDDEN)
//                .body(ApiResponse.error(403, "Access denied"));
//    }

    /* =========================
       401 - Autenticación
       ========================= */
//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<ApiResponse<Void>> handleUnauthorized(UnauthorizedException ex) {
//        return ResponseEntity
//                .status(HttpStatus.UNAUTHORIZED)
//                .body(ApiResponse.error(401, ex.getMessage()));
//    }

    /* =========================
       409 - Conflictos (duplicados)
       ========================= */
//    @ExceptionHandler(ConflictException.class)
//    public ResponseEntity<ApiResponse<Void>> handleConflict(ConflictException ex) {
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(ApiResponse.error(409, ex.getMessage()));
//    }
    
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>> handleDataIntegrityViolation(
            DataIntegrityViolationException ex) {

        String message = "Database error.";

        if (ex.getCause() != null && ex.getCause().getCause() != null) {
            String causeMessage = ex.getCause().getCause().getMessage().toLowerCase();

            if (causeMessage.contains("foreign key")) {
                message = "Referenceresource does not exist";
            } else if (causeMessage.contains("duplicate")) {
                message = "Duplicate value";
            }
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(409, message));
    }

    /* =========================
       500 - Error genérico
       ========================= */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handle500(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(500, "Internal server error"));
    }
}