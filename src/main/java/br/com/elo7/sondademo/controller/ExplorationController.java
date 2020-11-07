package br.com.elo7.sondademo.controller;


import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;
import br.com.elo7.sondademo.model.dto.ExplorationRequest;
import br.com.elo7.sondademo.model.dto.ExplorationResponse;
import br.com.elo7.sondademo.service.ExplorationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ExplorationController {


    @Autowired
    private ExplorationService explorationService;

    @PostMapping(path ="/sonda-demo/v1/explore")
    public ResponseEntity<ExplorationResponse> explore(@RequestBody ExplorationRequest explorationRequest) {
        try{
            Exploration exploration = explorationService.parseStringData(explorationRequest.getExplorationData());
            List<ExplorationProbe> explorationProbeUpdated;
            explorationProbeUpdated = explorationService.explore(exploration);
            String dataUpdated = explorationService.convertToString(explorationProbeUpdated);
            return new ResponseEntity<>(new ExplorationResponse(dataUpdated), HttpStatus.OK);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, " Invalid Exploration", e);
        }

    }


}

