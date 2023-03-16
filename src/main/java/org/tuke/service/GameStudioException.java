package org.tuke.service;

public class GameStudioException extends RuntimeException{

    public GameStudioException(Throwable cause) {
        super(cause);
    }

    public GameStudioException() {
    }

    public GameStudioException(String message) {
        super(message);
    }

    public GameStudioException(String message, Throwable cause) {
        super(message, cause);
    }
}
