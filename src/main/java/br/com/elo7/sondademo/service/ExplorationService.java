package br.com.elo7.sondademo.service;

import br.com.elo7.sondademo.model.Coordinates;
import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;
import br.com.elo7.sondademo.model.Grid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ExplorationService implements ExplorationServiceInterface {

    @Override
    public Exploration parseStringData(String data) {
        List<ExplorationProbe> explorationProbeList = new ArrayList<>();

        String[] split = data.split("\n");
        List <String> parseData = Arrays.asList(split);

        String gridCoordinate =  parseData.get(0);
        int maximumPointX = Character.getNumericValue(gridCoordinate.charAt(0));
        int maximumPointY = Character.getNumericValue(gridCoordinate.charAt(2));
        Coordinates maximumCoordinates = new Coordinates(maximumPointX, maximumPointY);
        Grid grid = new Grid(maximumCoordinates);

        for (int i = 1; i < parseData.size(); i += 2){
            String probe = parseData.get(i);

            int pointX = Character.getNumericValue(probe.charAt(0));
            int pointY = Character.getNumericValue(probe.charAt(2));
            char face = probe.charAt(4);
            String path = parseData.get(i+1);

            Coordinates coordinatesPosition = new Coordinates(pointX, pointY);
            ExplorationProbe explorationProbe = new ExplorationProbe(coordinatesPosition, face, path);
            explorationProbeList.add(explorationProbe);
        }

        return new Exploration(grid, explorationProbeList);

        }




    @Override
    public List<ExplorationProbe> explore(Exploration exploration) {
        List<ExplorationProbe> explorationProbeList = exploration.getExplorationProbeList();
        for (ExplorationProbe explorationProbe : explorationProbeList) {
            String path = explorationProbe.getPath();
            Coordinates coordinatesPosition = explorationProbe.getCoordinatesPosition();

            char face = explorationProbe.getFace();
            int startX = coordinatesPosition.getPointX();
            int startY = coordinatesPosition.getPointY();

            int j = 0;
            while (j < path.length()) {
                if (face == 'N') {
                    if (path.charAt(j) == 'M') {
                        startY = startY + 1;
                    } else if (path.charAt(j) == 'R') {
                        face = 'E';
                    } else if (path.charAt(j) == 'L') {
                        face = 'W';
                    }
                } else if (face == 'S') {
                    if (path.charAt(j) == 'M') {
                        startY = startY - 1;
                    } else if (path.charAt(j) == 'R') {
                        face = 'W';
                    } else if (path.charAt(j) == 'L') {
                        face = 'E';
                    }
                } else if (face == 'W') {
                    if (path.charAt(j) == 'M') {
                        startX = startX - 1;
                    } else if (path.charAt(j) == 'R') {
                        face = 'N';
                    } else if (path.charAt(j) == 'L') {
                        face = 'S';
                    }
                } else if (face == 'E') {
                    if (path.charAt(j) == 'M') {
                        startX = startX + 1;
                    } else if (path.charAt(j) == 'R') {
                        face = 'S';
                    } else if (path.charAt(j) == 'L') {
                        face = 'N';
                    }
                }
                j = j + 1;
            }

            coordinatesPosition.setPointX(startX);
            coordinatesPosition.setPointY(startY);
            explorationProbe.setFace(face);
        }

        return explorationProbeList;
    }



    @Override
    public String convertToString(List<ExplorationProbe> explorationProbeListUpdated) {
        String dataUpdated = " ";
        for (ExplorationProbe explorationProbe : explorationProbeListUpdated) {
            String pointX = Integer.toString(explorationProbe.getCoordinatesPosition().getPointX()) + " ";
            String pointY = Integer.toString(explorationProbe.getCoordinatesPosition().getPointY())+ " ";
            String face = Character.toString(explorationProbe.getFace());
            String probeUpdated = pointX + pointY + face + "\n";
            dataUpdated = dataUpdated + probeUpdated;
        }

        return dataUpdated;
    }





}
