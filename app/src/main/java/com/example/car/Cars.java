package com.example.car;

public class Cars {

    private int Id;
    private String Model;
    private Integer Mileage;
    private Integer Horsepower;
    private String Image;

    public Cars(int id, String model, int mileage, int horsepower, String image) {
        Id = id;
        Model = model;
        Mileage = mileage;
        Horsepower = horsepower;
        Image = image;
    }

    public void setModel(String model) {
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
    }
}
