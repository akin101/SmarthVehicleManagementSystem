package com.vehicle.model;

public class Car extends Vehicle{
    private boolean isElectric;

    public Car(String district, int emission, String ownerName, String vechicleId, boolean isElectric) {
        super(district, emission, ownerName, vechicleId);
        this.isElectric = isElectric;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    @Override
    public double calculateTax(){
        if(isElectric){
            return 50.0;
        }else{
            return getEmission()*5;
        }
    }

}
