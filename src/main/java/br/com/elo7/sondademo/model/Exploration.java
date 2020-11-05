package br.com.elo7.sondademo.model;


import java.util.List;

public class Exploration {
    private Grid grid;
    private List<ExplorationProbe> explorationProbeList;

    public Exploration(Grid grid, List<ExplorationProbe> explorationProbeList){
     this.grid = grid;
     this.explorationProbeList = explorationProbeList;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public List<ExplorationProbe> getExplorationProbeList() {
        return explorationProbeList;
    }

    public void setExplorationProbeList(List<ExplorationProbe> explorationProbeList) {
        this.explorationProbeList = explorationProbeList;
    }

    @Override
    public String toString() {
        return "Exploration{" +
                "grid=" + grid +
                ", explorationProbeList=" + explorationProbeList +
                '}';
    }
}
