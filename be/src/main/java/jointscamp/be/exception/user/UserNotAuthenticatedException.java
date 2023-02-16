package jointscamp.be.exception.user;

public class UserNotAuthenticatedException extends Exception{
    public UserNotAuthenticatedException() {
        super();
    }

    public UserNotAuthenticatedException(String message) {
        super(message);
    }
}
