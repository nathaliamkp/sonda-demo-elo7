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

//    "5 5\n" +
//    "1 2 N\n" +
//    "LMLMLMLMM";

    @Override
    public Exploration parseStringData(String data) {


        int pointX = Character.getNumericValue(data.charAt(0));
        int pointY = Character.getNumericValue(data.charAt(2));
        Coordinates maximumCoordinates = new Coordinates(pointX, pointY);
        Grid grid = new Grid(maximumCoordinates);


        List<ExplorationProbe> explorationProbeList = findProbesAndPaths(data);

        Exploration exploration = new Exploration(grid, explorationProbeList);
        return exploration;
        }

     private List<ExplorationProbe> findProbesAndPaths(String data){
         List<ExplorationProbe> explorationProbeList = new ArrayList<>();
         for (int i = 3; i < data.length(); i += 1) {
             if ((data.charAt(i - 1) == '\n') && Character.isDigit(data.charAt(i)) && Character.isDigit(data.charAt(i + 2))){
                 int pointX = Character.getNumericValue(data.charAt(i));
                 int pointY = Character.getNumericValue(data.charAt(i + 2));
                 char face = data.charAt(i + 4);
                 String startPath = data.substring(i + 6) ;
                    for (int j = 0; j < startPath.length(); j+= 1){
                        if ((startPath.charAt(j) == ' ') || ((j + 1) == startPath.length())) {
                            String path = " ";
                            if (startPath.charAt(j) == ' '){
                                path = startPath.substring(0, j);
                            } else if ((j + 1) == startPath.length()){
                                path = startPath;
                            }
                                Coordinates coordinatesPosition = new Coordinates(pointX, pointY);
                                ExplorationProbe explorationProbe = new ExplorationProbe(coordinatesPosition, face, path);
                                explorationProbeList.add(explorationProbe);

                                break;
                        }
                    }
             }
         }

         return explorationProbeList;
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
    public String convertToString(List<ExplorationProbe> explorationProbeList) {

        return null;
    }





}
