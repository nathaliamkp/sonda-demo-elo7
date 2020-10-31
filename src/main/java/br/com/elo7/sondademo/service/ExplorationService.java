package br.com.elo7.sondademo.service;

import br.com.elo7.sondademo.model.Coordinates;
import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExplorationService implements ExplorationServiceInterface {

    @Override
    public Exploration parseStringData(String data) {


        return null;
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
