package com.vehicle.exception;

public class InvalidEmissionException extends Exception{
    public InvalidEmissionException(String msg){
        System.out.println(msg);
    }
}
