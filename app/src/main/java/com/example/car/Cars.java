package com.example.car;

import java.io.Serializable;

public class Cars implements Serializable {

    private int Id_car;
    private Integer Id_model;
    private Integer Id_generation;
    private String Equipment;
    private Integer Id_transmission;
    private Integer Id_engine;
    private Integer Id_fuel;
    private Integer Id_drive;
    private Integer Id_body;
    private Integer Id_color;
    private Integer Id_wheel;
    private String VIN;
    private Integer Mileage;
    private String Image;

    public Cars(int id_car, Integer idModel, Integer idGeneration, String equipment, Integer idTransmission,
                Integer idEngine, Integer idFuel, Integer idDrive, Integer idBody, Integer idColor,
                Integer idWheel, String VIN, Integer mileage, String image) {

        if (id_car != 0) {
            Id_car = id_car;
        }
        Id_model = idModel;
        Id_generation = idGeneration;
        Equipment = equipment;
        Id_transmission = idTransmission;
        Id_engine = idEngine;
        Id_fuel = idFuel;
        Id_drive = idDrive;
        Id_body = idBody;
        Id_color = idColor;
        Id_wheel = idWheel;
        this.VIN = VIN;
        Mileage = mileage;
        Image = image;
    }

    public void setIdModel(Integer idModel) {
        Id_model = idModel;
    }

    public void setIdGeneration(Integer idGeneration) {
        Id_generation = idGeneration;
    }

    public void setEquipment(String Equipment) {
        this.Equipment = Equipment;
    }

    public void setIdTransmission(Integer idTransmission) {
        Id_transmission = idTransmission;
    }

    public void setIdEngine(Integer idEngine) {
        Id_engine = idEngine;
    }

    public void setIdFuel(Integer idFuel) {
        Id_fuel = idFuel;
    }

    public void setIdDrive(Integer idDrive) {
        Id_drive = idDrive;
    }

    public void setIdBody(Integer idBody) {
        Id_body = idBody;
    }

    public void setIdColor(Integer idColor) {
        Id_color = idColor;
    }

    public void setIdWheel(Integer idWheel) {
        Id_wheel = idWheel;
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

    public Integer getIdModel() {
        return Id_model;
    }

    public Integer getIdGeneration() {
        return Id_generation;
    }

    public String getEquipment() {
        return Equipment;
    }

    public Integer getIdTransmission() {
        return Id_transmission;
    }

    public Integer getIdEngine() {
        return Id_engine;
    }

    public Integer getIdFuel() {
        return Id_fuel;
    }

    public Integer getIdDrive() {
        return Id_drive;
    }

    public Integer getIdBody() {
        return Id_body;
    }

    public Integer getIdColor() {
        return Id_color;
    }

    public Integer getIdWheel() {
        return Id_wheel;
    }

    public String getVIN() {
        return VIN;
    }

    public Integer getMileage() {
        return Mileage;
    }

    public String getImage() {
        return Image;
    }
}