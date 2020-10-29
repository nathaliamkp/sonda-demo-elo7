package br.com.elo7.sondademo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Coordinates {

    private int maximumLeftPoint;
    private int maximumRightPoint;

    public Coordinates (int maximumLeftPoint, int maximumRightPoint){
        this.maximumLeftPoint = maximumLeftPoint;
        this.maximumRightPoint = maximumRightPoint;
    }
}
