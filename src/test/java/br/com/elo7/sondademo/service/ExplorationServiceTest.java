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
    void exploreWithTwoProbes() {


        ExplorationService explorationService = new ExplorationService();


        Exploration exploration = mockExploration();

        List<ExplorationProbe> explorationProbesUpdated = explorationService.explore(exploration);

        assertNotNull(explorationProbesUpdated);
        assertEquals(2, explorationProbesUpdated.size());


    }

    private Exploration mockExploration() {
        Coordinates maximumCoordinates = new Coordinates(5,5);
        Grid grid = new Grid(maximumCoordinates);

        List<ExplorationProbe> explorationProbeList = new ArrayList<>();

        Coordinates coordinatesPosition1 = new Coordinates(1,2);
        ExplorationProbe explorationProbe1 = new ExplorationProbe(coordinatesPosition1, 'N',"LMLMLMLMM" );
        explorationProbeList.add(explorationProbe1);

        Coordinates coordinatesPosition2 = new Coordinates(3,3);
        ExplorationProbe explorationProbe2 = new ExplorationProbe(coordinatesPosition2, 'E', "MMRMMRMRRM");
        explorationProbeList.add(explorationProbe2);


        return new Exploration(grid, explorationProbeList);
    }

    @Test
    void exploreWithOneProbes() {


        ExplorationService explorationService = new ExplorationService();

        Coordinates maximumCoordinates = new Coordinates(5,5);
        Grid grid = new Grid(maximumCoordinates);

        List<ExplorationProbe> explorationProbeList = new ArrayList<>();

        Coordinates coordinatesPosition1 = new Coordinates(1,2);
        ExplorationProbe explorationProbe1 = new ExplorationProbe(coordinatesPosition1, 'N',"LMLMLMLMM" );
        explorationProbeList.add(explorationProbe1);


        Exploration exploration = new Exploration(grid, explorationProbeList);

        List<ExplorationProbe> explorationProbesUpdated = explorationService.explore(exploration);

        assertNotNull(explorationProbesUpdated);
        assertEquals(1, explorationProbesUpdated.size());


    }

    @Test
    void extractDataWhenDataHasOneProbe(){
        ExplorationService explorationService = new ExplorationService();

        String data = "5 5 1 2 N LMLMLMLMM" ;

        Exploration exploration = explorationService.parseStringData(data);

        assertNotNull(exploration.getExplorationProbeList());
        assertEquals(1, exploration.getExplorationProbeList().size());

    }
}