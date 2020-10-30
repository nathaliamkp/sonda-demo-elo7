package br.com.elo7.sondademo.service;

import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ExplorationService implements ExplorationServiceInterface {

    @Override
    public Exploration parseStringData(String data) {


        return null;
    }

    @Override
    public List<ExplorationProbe> explore(Exploration exploration) {
        List<ExplorationProbe> explorationProbes = exploration.getExplorationProbeList();
        int index = explorationProbes.size();
        for(int i = 0; i <= index; i += 1){
            String instructions = explorationProbes.get(i).getPath();
            char direction = explorationProbes.get(i).getFace();
            int startX = explorationProbes.get(i).getCoordinatesPosition().getPointX();
            int starY = explorationProbes.get(i).getCoordinatesPosition().getPointY();

            int j = 0;
            while (j =! instructions.length()){
                if (direction == 'N'){
                    if
                }

            }

        }

        return new ArrayList<>();
    }

    @Override
    public String convertToString(List<ExplorationProbe> explorationProbeList) {

        return null;
    }


//    public List<ExplorationProbe> explorationParse(Exploration exploration){
//        List<ExplorationProbe> explorationProbes = new ArrayList<ExplorationProbe>();
//        String inicialData = exploration.getData();
//        int maximumLeftPoint = inicialData.charAt(0);
//        int maximumRightPoint = inicialData.charAt(3);
//
//        return null;
//
//    }



}
