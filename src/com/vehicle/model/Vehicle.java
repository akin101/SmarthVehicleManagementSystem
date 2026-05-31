package com.vehicle.model;

public abstract class Vehicle {
    private String vechicleId;
    private String ownerName;
    private String district;
    private int emission;

    public Vehicle(String district, int emission, String ownerName, String vechicleId) {
        this.district = district;
        this.emission = emission;
        this.ownerName = ownerName;
        this.vechicleId = vechicleId;

    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getEmission() {
        return emission;
    }

    public void setEmission(int emission) {
        this.emission = emission;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVechicleId() {
        return vechicleId;
    }

    public void setVechicleId(String vechicleId) {
        this.vechicleId = vechicleId;
    }

    public abstract double calculateTax();

    @Override
    public String toString(){
        return "Araç ID: " + getVechicleId() + " | Sahibi: " + ownerName + " | Hesaplanan Vergi: " + calculateTax() + " TL";
    }
}
