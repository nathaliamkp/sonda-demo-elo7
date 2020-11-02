package br.com.elo7.sondademo.service;

import br.com.elo7.sondademo.model.Coordinates;
import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;
import br.com.elo7.sondademo.model.Grid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ExplorationService implements ExplorationServiceInterface {

//    "5 5 1 2 N LMLMLMLMM"

    @Override
    public Exploration parseStringData(String data) {


        int pointX = Character.getNumericValue(data.charAt(0));
        int pointY = Character.getNumericValue(data.charAt(2));
        Coordinates maximumCoordinates = new Coordinates(pointX, pointY);
        Grid grid = new Grid(maximumCoordinates);

        List<ExplorationProbe> explorationProbeList = new ArrayList<>();

            for (int i = 3; i < data.length(); i += 1) {
                int verify = 0;
                if (data.charAt(i-1) == '0') {
                    verify =1;
                    int pointX = Character.getNumericValue(data.charAt(i));
                    int pointY = Character.getNumericValue(data.charAt(i + 2));
                    char face = data.charAt(i + 4);



                } else if (i == 4) {

                }
            }

        Exploration exploration = new Exploration(grid, explorationProbeList);
        return exploration;
        }



    @Override
    public List<ExplorationProbe> explore(Exploration exploration) {
        List<ExplorationProbe> explorationProbeList = exploration.getExplorationProbeList();
        for (ExplorationProbe explorationProbe : explorationProbeList) {
            String instructions = explorationProbe.getPath();
            char direction = explorationProbe.getFace();
            Coordinates coordinatesPosition = explorationProbe.getCoordinatesPosition();
            int startX = coordinatesPosition.getPointX();
            int startY = coordinatesPosition.getPointY();

            int j = 0;
            while (j < instructions.length()) {
                if (direction == 'N') {
                    if (instructions.charAt(j) == 'M') {
                        startY = startY + 1;
                    } else if (instructions.charAt(j) == 'R') {
                        direction = 'E';
                    } else if (instructions.charAt(j) == 'L') {
                        direction = 'W';
                    }
                } else if (direction == 'S') {
                    if (instructions.charAt(j) == 'M') {
                        startY = startY - 1;
                    } else if (instructions.charAt(j) == 'R') {
                        direction = 'W';
                    } else if (instructions.charAt(j) == 'L') {
                        direction = 'E';
                    }
                } else if (direction == 'W') {
                    if (instructions.charAt(j) == 'M') {
                        startX = startX - 1;
                    } else if (instructions.charAt(j) == 'R') {
                        direction = 'N';
                    } else if (instructions.charAt(j) == 'L') {
                        direction = 'S';
                    }
                } else if (direction == 'E') {
                    if (instructions.charAt(j) == 'M') {
                        startX = startX + 1;
                    } else if (instructions.charAt(j) == 'R') {
                        direction = 'S';
                    } else if (instructions.charAt(j) == 'L') {
                        direction = 'N';
                    }
                }
                j = j + 1;
            }

            coordinatesPosition.setPointX(startX);
            coordinatesPosition.setPointY(startY);
            explorationProbe.setFace(direction);
        }

        return explorationProbeList;
    }



    @Override
    public String convertToString(List<ExplorationProbe> explorationProbeList) {

        return null;
    }





}
