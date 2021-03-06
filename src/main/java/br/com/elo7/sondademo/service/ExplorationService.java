package br.com.elo7.sondademo.service;


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
            String face =  Character.toString(probe.get(2).charAt(0));
            String path = parseData.get(i + 1);


            Coordinates coordinatesPosition = new Coordinates(pointX, pointY);
            ExplorationProbe explorationProbe = new ExplorationProbe(coordinatesPosition, face, path);
            explorationProbeList.add(explorationProbe);
        }

        return new Exploration(grid, explorationProbeList);

    }

    private void validateExploration(Exploration exploration) throws RuntimeException{
        List<ExplorationProbe> explorationProbeList = exploration.getExplorationProbeList();
        for (ExplorationProbe explorationProbe : explorationProbeList) {
            if (exploration.getGrid().getMaximumCoordinates().getPointY() < explorationProbe.getCoordinatesPosition().getPointY() || exploration.getGrid().getMaximumCoordinates().getPointX() < explorationProbe.getCoordinatesPosition().getPointX()) {
                throw new RuntimeException("One of the probes has an invalid starting point");
            }
        }
    }

    @Override
    public List<ExplorationProbe> explore(Exploration exploration){
        validateExploration(exploration);
        List<ExplorationProbe> explorationProbeList = exploration.getExplorationProbeList();
        List<ExplorationProbe> explorationProbesUpdates = new ArrayList<>();
        for (ExplorationProbe explorationProbe : explorationProbeList) {
            String path = explorationProbe.getPath();
            Coordinates coordinatesPosition = explorationProbe.getCoordinatesPosition();

            String face = explorationProbe.getFace();
            int startX = coordinatesPosition.getPointX();
            int startY = coordinatesPosition.getPointY();
            int maximumPointY = exploration.getGrid().getMaximumCoordinates().getPointY();
            int maximumPointX = exploration.getGrid().getMaximumCoordinates().getPointX();

            explorationProbe = walking(path,  face, startX, startY, maximumPointY, maximumPointX, explorationProbesUpdates);
            explorationProbesUpdates.add(explorationProbe);

        }
            return explorationProbesUpdates;
    }

    private ExplorationProbe walking(String path, String face, int startX, int startY, int maximumPointY, int maximumPointX, List<ExplorationProbe> explorationProbesUpdated){
        int walk;
        int point;
        int j = 0;
        while (j < path.length()) {
            if (face.equals("N") ) {
                if (path.charAt(j) == 'M') {
                    walk = 1;
                    startY = pathIfProbreWouldPassGrid(startY, maximumPointY, walk);
                    point = startY;
                    startY = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = "NE";
                } else if (path.charAt(j) == 'L') {
                    face = "NW";
                }
            } else if (face.equals("S")){
                if (path.charAt(j) == 'M') {
                    walk = -1;
                    startY = pathIfProbreWouldPassGrid(startY, maximumPointY, walk);
                    point = startY;
                    startY = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = "SW";
                } else if (path.charAt(j) == 'L') {
                    face = "SE";
                }
            } else if (face.equals("W")) {
                if (path.charAt(j) == 'M') {
                    walk = -1;
                    startX = pathIfProbreWouldPassGrid(startX, maximumPointX, walk);
                    point = startX;
                    startX = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = "NW";
                } else if (path.charAt(j) == 'L') {
                    face = "SW";
                }
            } else if (face.equals("E")) {
                if (path.charAt(j) == 'M') {
                    walk = 1;
                    startX = pathIfProbreWouldPassGrid(startX, maximumPointX, walk);
                    point = startX;
                    startX = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = "SE";
                } else if (path.charAt(j) == 'L') {
                    face = "NE";
                }

            } else if (face.equals("NE")) {
                if (path.charAt(j) == 'M') {
                    walk = 1;
                    startY = pathIfProbreWouldPassGrid(startY, maximumPointY, walk);
                    walk = 1;
                    startX = pathIfProbreWouldPassGrid(startX, maximumPointX, walk);
                    point = startY;
                    startY = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                    point = startX;
                    startX = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = "E";
                } else if (path.charAt(j) == 'L') {
                    face = "N";
                }

            } else if (face.equals("NW")) {
                if (path.charAt(j) == 'M') {
                    walk = 1;
                    startY = pathIfProbreWouldPassGrid(startY, maximumPointY, walk);
                    walk = -1;
                    startX = pathIfProbreWouldPassGrid(startX, maximumPointX, walk);
                    point = startY;
                    startY = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                    point = startX;
                    startX = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = "N";
                } else if (path.charAt(j) == 'L') {
                    face = "W";
                }

            } else if (face.equals("SE")) {
                if (path.charAt(j) == 'M') {
                    walk = -1;
                    startY = pathIfProbreWouldPassGrid(startY, maximumPointY, walk);
                    walk = -1;
                    startX = pathIfProbreWouldPassGrid(startX, maximumPointX, walk);
                    point = startY;
                    startY = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                    point = startX;
                    startX = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = "S";
                } else if (path.charAt(j) == 'L') {
                    face = "E";
                }

            } else if (face.equals("SW")) {
                if (path.charAt(j) == 'M') {
                    walk = -1;
                    startY = pathIfProbreWouldPassGrid(startY, maximumPointY, walk);
                    walk = 1;
                    startX = pathIfProbreWouldPassGrid(startX, maximumPointX, walk);
                    point = startY;
                    startY = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                    point = startX;
                    startX = pathIfTheProbeFindsAnotherProbe(explorationProbesUpdated, startY, startX, point, walk);
                } else if (path.charAt(j) == 'R') {
                    face = "W";
                } else if (path.charAt(j) == 'L') {
                    face = "S";
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

    private int pathIfProbreWouldPassGrid (int point, int maximumPoint, int walk) {
        point = point + walk;
        if (point < maximumPoint){
            return point;
        } else return maximumPoint;
    }



    @Override
    public String convertToString(List<ExplorationProbe> explorationProbeListUpdated) {
        String dataUpdated = "";
        for (ExplorationProbe explorationProbe : explorationProbeListUpdated) {
            String pointX = explorationProbe.getCoordinatesPosition().getPointX() + " ";
            String pointY = explorationProbe.getCoordinatesPosition().getPointY() + " ";
            String face = (explorationProbe.getFace());
            String probeUpdated = pointX + pointY + face + "\n";
            dataUpdated += probeUpdated;
        }

        return dataUpdated;
    }





}
