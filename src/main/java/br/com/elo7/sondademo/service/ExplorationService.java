package br.com.elo7.sondademo.service;

import br.com.elo7.sondademo.Exception.InvalidExplorationException;
import br.com.elo7.sondademo.model.Coordinates;
import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;
import br.com.elo7.sondademo.model.Grid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//"10 10"

@Service
public class ExplorationService implements ExplorationServiceInterface {



    @Override
    public Exploration parseStringData(String data) {
        List<ExplorationProbe> explorationProbeList = new ArrayList<>();

        String[] split = data.split("\n");
        List<String> parseData = Arrays.asList(split);


        String gridCoordinate = parseData.get(0);
        String[] coordinate = gridCoordinate.split(" ");
        List<String> coordinatesGrid = Arrays.asList(coordinate);


        int maximumPointX = Integer.parseInt(coordinatesGrid.get(0));
        int maximumPointY = Integer.parseInt(coordinatesGrid.get(1));
        Coordinates maximumCoordinates = new Coordinates(maximumPointX, maximumPointY);
        Grid grid = new Grid(maximumCoordinates);

        for (int i = 1; i < parseData.size(); i += 2) {
            String probeData = parseData.get(i);
            String[] probeSplit = probeData.split(" ");
            List<String> probe = Arrays.asList(probeSplit);


            int pointX = Integer.parseInt(probe.get(0));
            int pointY = Integer.parseInt(probe.get(1));
            char face = probe.get(2).charAt(0);
            String path = parseData.get(i + 1);


            Coordinates coordinatesPosition = new Coordinates(pointX, pointY);
            ExplorationProbe explorationProbe = new ExplorationProbe(coordinatesPosition, face, path);
            explorationProbeList.add(explorationProbe);
        }

        return new Exploration(grid, explorationProbeList);

    }

    private void validateExploration(Exploration exploration) throws InvalidExplorationException {
        List<ExplorationProbe> explorationProbeList = exploration.getExplorationProbeList();
        for (ExplorationProbe explorationProbe : explorationProbeList) {
            if (exploration.getGrid().getMaximumCoordinates().getPointY() < explorationProbe.getCoordinatesPosition().getPointY() || exploration.getGrid().getMaximumCoordinates().getPointX() < explorationProbe.getCoordinatesPosition().getPointX()) {
                throw new InvalidExplorationException("Invalid Exploration: one of the probes will be out of the ");
            }
        }
    }

    @Override
    public List<ExplorationProbe> explore(Exploration exploration) {
        validateExploration(exploration);
        List<ExplorationProbe> explorationProbeList = exploration.getExplorationProbeList();
        List<ExplorationProbe> explorationProbesUpdates = new ArrayList<>();
        for (ExplorationProbe explorationProbe : explorationProbeList) {
            String path = explorationProbe.getPath();
            Coordinates coordinatesPosition = explorationProbe.getCoordinatesPosition();

            char face = explorationProbe.getFace();
            int startX = coordinatesPosition.getPointX();
            int startY = coordinatesPosition.getPointY();
            int maximumPointY = exploration.getGrid().getMaximumCoordinates().getPointY();
            int maximumPointX = exploration.getGrid().getMaximumCoordinates().getPointX();

            explorationProbe = walking(path,face, startX, startY, maximumPointY, maximumPointX, explorationProbesUpdates);
            explorationProbesUpdates.add(explorationProbe);

        }
            return explorationProbesUpdates;
    }

    private ExplorationProbe walking(String path, char face, int startX, int startY, int maximumPointY, int maximumPointX, List<ExplorationProbe> explorationProbesUpdated){
        int walk;
        int point;
        int j = 0;
        while (j < path.length()) {
            if (face == 'N') {
                if (path.charAt(j) == 'M') {
                    walk = 1;
                    startY = pathIfProbreWouldPassGrid(startY, maximumPointY, walk);
                    point = startY;
                    startY = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = 'E';
                } else if (path.charAt(j) == 'L') {
                    face = 'W';
                }
            } else if (face == 'S') {
                if (path.charAt(j) == 'M') {
                    walk = -1;
                    startY = pathIfProbreWouldPassGrid(startY, maximumPointY, walk);
                    point = startY;
                    startY = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = 'W';
                } else if (path.charAt(j) == 'L') {
                    face = 'E';
                }
            } else if (face == 'W') {
                if (path.charAt(j) == 'M') {
                    walk = -1;
                    startX = pathIfProbreWouldPassGrid(startX, maximumPointX, walk);
                    point = startX;
                    startX = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = 'N';
                } else if (path.charAt(j) == 'L') {
                    face = 'S';
                }
            } else if (face == 'E') {
                if (path.charAt(j) == 'M') {
                    walk = 1;
                    startX = pathIfProbreWouldPassGrid(startX, maximumPointX, walk);
                    point = startX;
                    startX = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = 'S';
                } else if (path.charAt(j) == 'L') {
                    face = 'N';
                }
            }
            j = j + 1;
        }
        Coordinates coordinatesPosition = new Coordinates(startX,startY);
        return new ExplorationProbe(coordinatesPosition,face, path);
    }


    private int pathIfTheProbeFindsAnotherProbe ( List<ExplorationProbe> explorationProbesUpdated, int startY, int startX, int point, int walk) {
        for (ExplorationProbe explorationProbe: explorationProbesUpdated){
            if (startX == explorationProbe.getCoordinatesPosition().getPointX() && startY == explorationProbe.getCoordinatesPosition().getPointY() ){
                point = point - walk;
            }

        }
        return point;
    }

    private int pathIfProbreWouldPassGrid (int point, int maximumPoit, int walk) {
        point = point + walk;
        if (point < maximumPoit){
            return point;
        } else return maximumPoit;
    }



    @Override
    public String convertToString(List<ExplorationProbe> explorationProbeListUpdated) {
        String dataUpdated = "";
        for (ExplorationProbe explorationProbe : explorationProbeListUpdated) {
            String pointX = explorationProbe.getCoordinatesPosition().getPointX() + " ";
            String pointY = explorationProbe.getCoordinatesPosition().getPointY() + " ";
            String face = Character.toString(explorationProbe.getFace());
            String probeUpdated = pointX + pointY + face + "\n";
            dataUpdated += probeUpdated;
        }

        return dataUpdated;
    }





}
