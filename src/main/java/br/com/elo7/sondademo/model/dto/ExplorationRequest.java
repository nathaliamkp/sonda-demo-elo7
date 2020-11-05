package br.com.elo7.sondademo.model.dto;

public class ExplorationRequest {
    private String explorationData;

    public ExplorationRequest() {
    }

    public ExplorationRequest(String explorationData) {
        this.explorationData = explorationData;
    }

    public String getExplorationData() {
        return explorationData;
    }

    public void setExplorationData(String explorationData) {
        this.explorationData = explorationData;
    }

    @Override
    public String toString() {
        return "ExplorationRequest{" +
                "explorationData='" + explorationData + '\'' +
                '}';
    }
}
