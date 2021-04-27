package org.deviceservice.app.exception;

public class UserFriendlyException extends RuntimeException {

    public UserFriendlyException(String message) {
        super(message);
    }
}
