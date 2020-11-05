package br.com.elo7.sondademo.model.dto;

public class ExplorationResponse {
   private String dataUpdated;

    public ExplorationResponse() {
    }

    public ExplorationResponse(String dataUpdated) {
        this.dataUpdated = dataUpdated;
    }

    public String getDataUpdated() {
        return dataUpdated;
    }

    public void setDataUpdated(String dataUpdated) {
        this.dataUpdated = dataUpdated;
    }

    @Override
    public String toString() {
        return "ExplorationResponse{" +
                "dataUpdated='" + dataUpdated + '\'' +
                '}';
    }
}
