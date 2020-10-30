package br.com.elo7.sondademo.service;

import br.com.elo7.sondademo.model.Exploration;
import br.com.elo7.sondademo.model.ExplorationProbe;

import java.util.List;

public interface ExplorationServiceInterface {


    public Exploration parseStringData (String data);

    public List<ExplorationProbe> explore (Exploration explore);

    public String convertToString (List<ExplorationProbe> explorationProbeList );
}
