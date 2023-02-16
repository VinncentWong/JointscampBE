package jointscamp.be.exception;

public class UserNotAuthenticatedException extends Exception{
    public UserNotAuthenticatedException() {
        super();
    }

    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}
