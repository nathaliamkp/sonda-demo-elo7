package br.com.elo7.sondademo.model;


public class ExplorationProbe {
    Coordinates coordinatesPosition;
    private String face;
    private String path;

    public Coordinates getCoordinatesPosition() {
        return coordinatesPosition;
    }

    public void setCoordinatesPosition(Coordinates coordinatesPosition) {
        this.coordinatesPosition = coordinatesPosition;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ExplorationProbe(Coordinates coordinatesPosition, String face, String path) {
        this.coordinatesPosition = coordinatesPosition;
        this.face = face;
        this.path = path;


    }

    @Override
    public String toString() {
        return "ExplorationProbe{" +
                "coordinatesPosition=" + coordinatesPosition +
                ", face=" + face +
                ", path='" + path + '\'' +
                '}';
    }
}
