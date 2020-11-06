package br.com.elo7.sondademo.Exception;

public class InvalidExplorationException extends RuntimeException {
    String message;

    public InvalidExplorationException(String s, String message) {
        super(s);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InvalidExplorationException(String message) {
        this.message = message;
    }
}
