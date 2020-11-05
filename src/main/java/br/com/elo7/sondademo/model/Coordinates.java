package br.com.elo7.sondademo.model;


public class Coordinates {

 private int pointX;
 private int pointY;

    public Coordinates(int pointX, int pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public int getPointX() {
        return pointX;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "pointX=" + pointX +
                ", pointY=" + pointY +
                '}';
    }
}
