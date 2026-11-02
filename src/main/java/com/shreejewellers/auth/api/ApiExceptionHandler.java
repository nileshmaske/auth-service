package com.shreejewellers.auth.api;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler({BadCredentialsException.class,DisabledException.class})
 ResponseEntity<Map<String,String>> invalidCredentials(Exception e){
  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error","Invalid credentials"));
 }
 @ExceptionHandler(MethodArgumentNotValidException.class)
 ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException e){
  return ResponseEntity.badRequest().body(Map.of("error","Username and password are required"));
 }
}
