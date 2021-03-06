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
    void exploreWithTwoProbes() throws Exception {
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

        Coordinates coordinatesPosition1 = new Coordinates(3,3);
        ExplorationProbe explorationProbe1 = new ExplorationProbe(coordinatesPosition1, "W","RMM" );
        explorationProbeList.add(explorationProbe1);

        Coordinates coordinatesPosition2 = new Coordinates(1,3);
        ExplorationProbe explorationProbe2 = new ExplorationProbe(coordinatesPosition2, "E", "MMRMMRMRRM");
        explorationProbeList.add(explorationProbe2);


        return new Exploration(grid, explorationProbeList);
    }

    private Exploration mockExplorationWithProbesShockingAgainstProbes(){
        Coordinates maximumCoordinates = new Coordinates(5,5);
        Grid grid = new Grid(maximumCoordinates);

        List<ExplorationProbe> explorationProbeList = new ArrayList<>();

        Coordinates coordinatesPosition1 = new Coordinates(1,2);
        ExplorationProbe explorationProbe1 = new ExplorationProbe(coordinatesPosition1, "N","M" );
        explorationProbeList.add(explorationProbe1);

        Coordinates coordinatesPosition2 = new Coordinates(2,3);
        ExplorationProbe explorationProbe2 = new ExplorationProbe(coordinatesPosition2, "W", "M");
        explorationProbeList.add(explorationProbe2);

        return new Exploration(grid, explorationProbeList);
    }


    private Exploration mockExploratioPobreShokingAgainstGrid(){
        Coordinates maximumCoordinates = new Coordinates(5,5);
        Grid grid = new Grid(maximumCoordinates);

        List<ExplorationProbe> explorationProbeList = new ArrayList<>();

        Coordinates coordinatesPosition1 = new Coordinates(4,0);
        ExplorationProbe explorationProbe1 = new ExplorationProbe(coordinatesPosition1, "N","MMRMMMRM");
        explorationProbeList.add(explorationProbe1);



        return new Exploration(grid, explorationProbeList);
    }

    @Test
    void exploreWithProbeShockingAgainstGrid() throws Exception {
        ExplorationService explorationService = new ExplorationService();

        Exploration exploration = mockExploratioPobreShokingAgainstGrid();
        List<ExplorationProbe> explorationProbesUpdated = explorationService.explore(exploration);

        assertNotNull(explorationProbesUpdated);
        assertEquals(1, explorationProbesUpdated.size());

    }

    @Test
    void exploreWithProbeShokingAgainstAnotherProbe() throws Exception {
        ExplorationService explorationService = new ExplorationService();


        Exploration exploration = mockExplorationWithProbesShockingAgainstProbes();
        List<ExplorationProbe> explorationProbesUpdated = explorationService.explore(exploration);

        assertNotNull(explorationProbesUpdated);
        assertEquals(2, explorationProbesUpdated.size());

    }

    @Test
    void exploreWithOneProbes() throws Exception {

        ExplorationService explorationService = new ExplorationService();

        Exploration exploration = mockExploration();
        exploration.getExplorationProbeList().remove(1);

        List<ExplorationProbe> explorationProbesUpdated = explorationService.explore(exploration);

        assertNotNull(explorationProbesUpdated);
        assertEquals(1, explorationProbesUpdated.size());
        assertEquals(1,explorationProbesUpdated.get(0).getCoordinatesPosition().getPointX());
        assertEquals(5,explorationProbesUpdated.get(0).getCoordinatesPosition().getPointY());
        assertEquals("NW",explorationProbesUpdated.get(0).getFace());




    }

    @Test
    void extractDataWhenDataHasOneProbe(){
        String data = "10 10\n" +
                "1 2 N\n" +
                "LMLMLMLMM";
        ExplorationService explorationService = new ExplorationService();


        Exploration exploration = explorationService.parseStringData(data);

        assertNotNull(exploration.getExplorationProbeList());
        assertEquals(1, exploration.getExplorationProbeList().size());

    }

    @Test
    void extractDataWhenItHasMoreThanOneProbe(){
        String data = "5 5\n" +
                "1 2 N\n" +
                "LMLMLMLMM\n" +
                "3 3 E\n" +
                "MMRMMRMRRM";
        ExplorationService explorationService = new ExplorationService();


        Exploration exploration = explorationService.parseStringData(data);

        assertNotNull(exploration.getExplorationProbeList());
        assertEquals(2, exploration.getExplorationProbeList().size());

    }



    @Test
    void returnStringFromOneProbe() throws Exception {

        ExplorationService explorationService = new ExplorationService();


        Exploration exploration = mockExploration();

        List<ExplorationProbe> explorationProbesUpdated = explorationService.explore(exploration);

        String dataUpdated = explorationService.convertToString(explorationProbesUpdated);

        assertNotNull(dataUpdated);



    }


}