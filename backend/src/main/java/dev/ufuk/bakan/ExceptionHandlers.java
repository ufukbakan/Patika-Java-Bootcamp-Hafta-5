package dev.ufuk.bakan;

import javax.xml.ws.http.HTTPException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

@ControllerAdvice
public class ExceptionHandlers {
    
    @ExceptionHandler(HTTPException.class)
    public ResponseEntity<String> handleForbidden(HTTPException exception){
        return new ResponseEntity<String>(HttpStatus.valueOf(exception.getStatusCode()).toString(), HttpStatus.valueOf(exception.getStatusCode()));
    }
}
