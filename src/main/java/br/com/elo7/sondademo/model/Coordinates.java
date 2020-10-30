package br.com.elo7.sondademo.model;


public class Coordinates {

    private int maximumLeftPoint;

    public int getMaximumLeftPoint() {
        return maximumLeftPoint;
    }

    public void setMaximumLeftPoint(int maximumLeftPoint) {
        this.maximumLeftPoint = maximumLeftPoint;
    }

    public int getMaximumRightPoint() {
        return maximumRightPoint;
    }

    public void setMaximumRightPoint(int maximumRightPoint) {
        this.maximumRightPoint = maximumRightPoint;
    }

    private int maximumRightPoint;

    public Coordinates (int maximumLeftPoint, int maximumRightPoint){
        this.maximumLeftPoint = maximumLeftPoint;
        this.maximumRightPoint = maximumRightPoint;
    }


}
