package com.vehicle.app;
import com.vehicle.manage.CityVehicleManager;
import java.io.IOException;
import java.nio.file.*;
public class Main {
    public static void main(String[] args){
        CityVehicleManager manager = new CityVehicleManager();
        Path txt = Paths.get("data","carInfos.txt");
        System.out.println("=============================");
        System.out.println("----SMARTH CITY CAR SYSTEM---- ");
        manager.processVehicleFile(txt);

        System.out.println("Cars has high tax: ");
        manager.printHighTaxVehicle();
        System.out.println("ALL VEHICLES");
        manager.printInfos();
        System.out.println("----------------");
        System.out.println("test senario over");
    }
}
