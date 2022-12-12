package com.example.car;

import java.io.Serializable;

public class Cars implements Serializable {

    private int Id;
    private Integer IdModel;
    private Integer IdGeneration;
    private String Equipment;
    private Integer IdTransmission;
    private Integer IdEngine;
    private Integer IdFuel;
    private Integer IdDrive;
    private Integer IdBody;
    private Integer IdColor;
    private Integer IdWheel;
    private String VIN;
    private Integer Mileage;
    private String Image;

    public Cars(int id, Integer idModel, Integer idGeneration, String equipment, Integer idTransmission,
                Integer idEngine, Integer idFuel, Integer idDrive, Integer idBody, Integer idColor,
                Integer idWheel, String VIN, Integer mileage, String image) {
        if (id != 0) {
            Id = id;
        }
        IdModel = idModel;
        IdGeneration = idGeneration;
        Equipment = equipment;
        IdTransmission = idTransmission;
        IdEngine = idEngine;
        IdFuel = idFuel;
        IdDrive = idDrive;
        IdBody = idBody;
        IdColor = idColor;
        IdWheel = idWheel;
        this.VIN = VIN;
        Mileage = mileage;
        Image = image;
    }

    public void setIdModel(Integer idModel) {
        IdModel = idModel;
    }

    public void setIdGeneration(Integer idGeneration) {
        IdGeneration = idGeneration;
    }

    public void setEquipment(String Equipment) {
        this.Equipment = Equipment;
    }

    public void setIdTransmission(Integer idTransmission) {
        IdTransmission = idTransmission;
    }

    public void setIdEngine(Integer idEngine) {
        IdEngine = idEngine;
    }

    public void setIdFuel(Integer idFuel) {
        IdFuel = idFuel;
    }

    public void setIdDrive(Integer idDrive) {
        IdDrive = idDrive;
    }

    public void setIdBody(Integer idBody) {
        IdBody = idBody;
    }

    public void setIdColor(Integer idColor) {
        IdColor = idColor;
    }

    public void setIdWheel(Integer idWheel) {
        IdWheel = idWheel;
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
        return Id;
    }

    public Integer getIdModel() {
        return IdModel;
    }

    public Integer getIdGeneration() {
        return IdGeneration;
    }

    public String getEquipment() {
        return Equipment;
    }

    public Integer getIdTransmission() {
        return IdTransmission;
    }

    public Integer getIdEngine() {
        return IdEngine;
    }

    public Integer getIdFuel() {
        return IdFuel;
    }

    public Integer getIdDrive() {
        return IdDrive;
    }

    public Integer getIdBody() {
        return IdBody;
    }

    public Integer getIdColor() {
        return IdColor;
    }

    public Integer getIdWheel() {
        return IdWheel;
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

    /*public Cars(int id, String model, int mileage, int horsepower, String image) {
        if (id != 0) {
            Id = id;
        }
        Model = model;
        Mileage = mileage;
        Horsepower = horsepower;
        Image = image;
    }*/

    /*public void setModel(String model) {
        Model = model;
    }

    public void setMileage(Integer mileage) {
        Mileage = mileage;
    }

    public void setHorsepower(Integer horsepower) {
        Horsepower = horsepower;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public String getModel() {
        return Model;
    }

    public Integer getMileage() {
        return Mileage;
    }

    public Integer getHorsepower() {
        return Horsepower;
    }

    public String getImage() {
        return Image;
    }*/
}