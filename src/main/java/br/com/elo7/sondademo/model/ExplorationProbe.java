package br.com.elo7.sondademo.model;


public class ExplorationProbe {
    Coordinates coordinatesPosition;
    private char face;
    private String path;

    public Coordinates getCoordinatesPosition() {
        return coordinatesPosition;
    }

    public void setCoordinatesPosition(Coordinates coordinatesPosition) {
        this.coordinatesPosition = coordinatesPosition;
    }

    public char getFace() {
        return face;
    }

    public void setFace(char face) {
        this.face = face;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ExplorationProbe(Coordinates coordinatesPosition, char face, String path) {
        this.coordinatesPosition = coordinatesPosition;
        this.face = face;
        this.path = path;


    }
}
