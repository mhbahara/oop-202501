package com.upb.agripos.exception;

/**
 * Bab 9: Custom Exception untuk validasi
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}