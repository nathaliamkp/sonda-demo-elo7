package br.com.elo7.sondademo.model.dto;

public class InvalidExploration {
    String message;

    public InvalidExploration() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InvalidExploration(String message) {
        this.message = message;
    }
}
