package br.com.elo7.sondademo.service;

import br.com.elo7.sondademo.model.Coordinates;
import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;
import br.com.elo7.sondademo.model.Grid;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExplorationServiceTest {

    @Test
    void explore() {
//        Exploration exploration  = new Exploration("5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM");
//
//        explore(Exploration exploration);


        ExplorationService explorationService = new ExplorationService();
        Coordinates maximumCoordinates = new Coordinates(5,5);
        Coordinates coordinatesPosition = new Coordinates(1,2);
        Grid grid = new Grid(maximumCoordinates);
        ExplorationProbe explorationProbe = new ExplorationProbe(coordinatesPosition, 'N',"LMLMLMLMM" );
        Exploration exploration = new Exploration();
        explorationService.explore(exploration);
    }
}