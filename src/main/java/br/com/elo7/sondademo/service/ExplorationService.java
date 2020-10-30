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
    public List<ExplorationProbe> explore(Exploration explore) {


        return null;
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
