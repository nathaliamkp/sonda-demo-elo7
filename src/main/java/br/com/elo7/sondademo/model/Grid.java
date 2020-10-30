package br.com.elo7.sondademo.model;


public class Grid {
   Coordinates maximumCoordinates;


   public Grid(Coordinates maximumCoordinates) {
      this.maximumCoordinates = maximumCoordinates;
   }

   public Coordinates getMaximumCoordinates() {
      return maximumCoordinates;
   }

   public void setMaximumCoordinates(Coordinates maximumCoordinates) {
      this.maximumCoordinates = maximumCoordinates;
   }




}
