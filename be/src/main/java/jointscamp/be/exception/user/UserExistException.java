package jointscamp.be.exception.user;

public class UserExistException extends Exception{
    public UserExistException() {
        super();
    }

    public UserExistException(String message) {
        super(message);
    }
}
