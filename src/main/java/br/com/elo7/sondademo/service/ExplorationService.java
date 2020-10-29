package br.com.elo7.sondademo.service;

import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExplorationService {

   Exploration  exploration  = new Exploration("5 5 1 2 N LMLMLMLMM 3 3 E MMRMMRMRRM");

    public List<ExplorationProbe> explore (Exploration exploration){
        int maximumLeftPoint = 0;
        int maximumRightPoint = 0;
        String inicialData = exploration.getData();
        maximumLeftPoint = inicialData.charAt(0);






    }


}
