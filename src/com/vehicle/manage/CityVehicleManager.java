package com.vehicle.manage;

import com.vehicle.exception.InvalidEmissionException;
import com.vehicle.model.Car;
import com.vehicle.model.Truck;
import com.vehicle.model.Vehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CityVehicleManager {
    private ArrayList<DistrictFolder> districts;

    public CityVehicleManager() {
        this.districts = new ArrayList<>();
    }

    public ArrayList<DistrictFolder> getDistricts() {
        return districts;
    }

    public void setDistricts(ArrayList<DistrictFolder> districts) {
        this.districts = districts;
    }

    private class DistrictFolder{
        private String districtName;
        private ArrayList<Vehicle> vehicles;

        public DistrictFolder(String districtName) {
            this.districtName = districtName;
            this.vehicles = new ArrayList<>();
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public ArrayList<Vehicle> getVehicles() {
            return vehicles;
        }

        public void setVehicles(ArrayList<Vehicle> vehicles) {
            this.vehicles = vehicles;
        }
    }
    public void addVehicleToDistrict(String district, Vehicle vehicle){
        for(DistrictFolder d : districts){
            if(d.getDistrictName().equals(district)){
                d.getVehicles().add(vehicle);
                return;
            }
        }
        DistrictFolder newDistrict = new DistrictFolder(district);
        newDistrict.getVehicles().add(vehicle);
        districts.add(newDistrict);

    }

    public void processVehicleFile(Path inputPath) {
        try (BufferedReader br = Files.newBufferedReader(inputPath)) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    if (line.trim().isEmpty()) continue;
                    String[] arr = line.split(",");
                    if (arr.length < 6) {
                        System.out.println("Sıra eksik veri içeriyor, atlandı: " + line);
                        continue;
                    }

                    String type = arr[0].trim().toUpperCase();
                    String plate = arr[1].trim();
                    String name = arr[2].trim();
                    String district = arr[3].trim();
                    int emission = Integer.parseInt(arr[4].trim());
                    double specialParam = Double.parseDouble(arr[5].trim());

                    if (emission < 0 || emission > 500) {
                        throw new InvalidEmissionException("Kritik Emisyon Hatası (0-500 arası olmalı): " + emission);
                    }
                    Vehicle vehicle = null;

                    if (type.equals("CAR")) {
                        boolean isElectric = (specialParam == 1.0); // 1 ise elektrikli, 0 ise değil

                        vehicle = new Car(district, emission, name, plate, isElectric);
                    } else if (type.equals("TRUCK")) {
                        vehicle = new Truck(district, emission, name, plate, specialParam);
                    } else {
                        System.out.println("Bilinmeyen araç tipi atlandı: " + type);
                        continue;
                    }
                    addVehicleToDistrict(district, vehicle);

                } catch (InvalidEmissionException e) {
                    System.out.println("Hata: " + e.getMessage() + " -> Satır atlandı.");
                } catch (NumberFormatException e) {
                    System.out.println("Sayı formatı hatası, satır atlandı: " + line);
                } catch (Exception e) {
                    System.out.println("Beklenmeyen hata: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
    public void printHighTaxVehicle() {
        for (DistrictFolder d : districts) {
            for (Vehicle x : d.getVehicles()) {

                if (x.calculateTax() > 1000) {
                    System.out.println(x + " -> " + d.getDistrictName());
                }
            }
        }
    }
    public void printInfos(){
        for(DistrictFolder d : districts){
            for(Vehicle v : d.getVehicles()){
                System.out.println(v);
            }
        }
    }

}