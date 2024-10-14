package pl.infoshare.clinicweb.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerApp {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException exception) {
        Map<String, String> errorsMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach((error) -> {

            errorsMap.put(error.getField(), error.getDefaultMessage());

        });
        return errorsMap;

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleException(EntityNotFoundException exception) {

        Map<String, String> errorsMap = new HashMap<>();
        errorsMap.put("error", exception.getMessage());
        return errorsMap;

    }
}
