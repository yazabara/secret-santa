package tver.wa.exceptions;

public class UserNotFoundException extends EventHandledException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
