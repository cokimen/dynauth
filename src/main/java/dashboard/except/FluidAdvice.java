package dashboard.except;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FluidAdvice {
    @ExceptionHandler
    public ResponseEntity<String> handleGeneral(Exception ex){
        return new ResponseEntity<>("Exception General Handling "+ex.getMessage(), HttpStatus.ALREADY_REPORTED);
    }


    @ExceptionHandler( AnonException.class)
    public ResponseEntity<Object> handleAnonException(AnonException an){
        return new ResponseEntity<>("Exception AnonException Handling "+an.getMessage(), HttpStatus.ALREADY_REPORTED);
    }
}
