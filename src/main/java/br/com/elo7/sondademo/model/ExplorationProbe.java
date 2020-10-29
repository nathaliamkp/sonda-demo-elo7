package br.com.elo7.sondademo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExplorationProbe {
    Coordinates coordinatesPosition;
    private String face;
    private String path;
}
