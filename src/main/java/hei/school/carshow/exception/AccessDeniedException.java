package hei.school.carshow.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends ApiException{
    public AccessDeniedException() {
        super("Access Denied", HttpStatus.FORBIDDEN);
    }
    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
