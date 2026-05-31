package com.vehicle.model;

public class Truck extends Vehicle{
    private double maxCargoWeight;

    public Truck(String district, int emission, String ownerName, String vechicleId, double maxCargoWeight) {
        super(district, emission, ownerName, vechicleId);
        this.maxCargoWeight = maxCargoWeight;
    }

    public double getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(double maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }
    @Override
    public double calculateTax(){
        return (getEmission()*8) + (maxCargoWeight * 0.1);
    }
}
