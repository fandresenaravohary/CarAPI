package hei.school.carshow.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistException extends ApiException {
    public UserAlreadyExistException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public UserAlreadyExistException() {
        super("User already exist with the provided information", HttpStatus.BAD_REQUEST);
    }
}
