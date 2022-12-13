package com.example.car;

import java.io.Serializable;

public class CarsValue implements Serializable {

    private int Id_car;
    private String Brand;
    private String Model;
    private String Generation;
    private String Equipment;
    private String Transmission;
    private String Engine;
    private String Horsepower;
    private String Fuel;
    private String Drive;
    private String Body;
    private String Color;
    private String Wheel;
    private String VIN;
    private Integer Mileage;
    private String Image;

    public CarsValue(int id, String brand, String model, String generation, String equipment,
                     String transmission, String engine, String horsepower,
                     String fuel, String drive, String body, String color,
                     String wheel, String VIN, Integer mileage, String image) {
        Id_car = id;
        Brand = brand;
        Model = model;
        Generation = generation;
        Equipment = equipment;
        Transmission = transmission;
        Engine = engine;
        Horsepower = horsepower;
        Fuel = fuel;
        Drive = drive;
        Body = body;
        Color = color;
        Wheel = wheel;
        this.VIN = VIN;
        Mileage = mileage;
        Image = image;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setModel(String model) {
        Model = model;
    }

    public void setGeneration(String generation) {
        Generation = generation;
    }

    public void setEquipment(String Equipment) {
        this.Equipment = Equipment;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }

    public void setEngine(String engine) {
        Engine = engine;
    }

    public void setHorsepower(String horsepower) {
        Horsepower = horsepower;
    }

    public void setFuel(String fuel) {
        Fuel = fuel;
    }

    public void setDrive(String drive) {
        Drive = drive;
    }

    public void setBody(String body) {
        Body = body;
    }

    public void setColors(String color) {
        Color = color;
    }

    public void setWheel(String wheel) {
        Wheel = wheel;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public void setMileage(Integer mileage) {
        Mileage = mileage;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getId() {
        return Id_car;
    }

    public String getBrand() {
        return Brand;
    }

    public String getModel() {
        return Model;
    }

    public String getGeneration() {
        return Generation;
    }

    public String getEquipment() {
        return Equipment;
    }

    public String getTransmission() {
        return Transmission;
    }

    public String getEngine() {
        return Engine;
    }

    public String getHorsepower() {
        return Horsepower;
    }

    public String getFuel() {
        return Fuel;
    }

    public String getDrive() {
        return Drive;
    }

    public String getBody() {
        return Body;
    }

    public String getColors() {
        return Color;
    }

    public String getWheel() {
        return Wheel;
    }

    public String getVIN() {
        return VIN;
    }

    public Integer getMileage() {
        return Mileage;
    }

    public String getImage() {
        if (Image == null) {
            return "null";
        }
        return Image;
    }
}