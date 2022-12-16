package com.example.car;

public class Services_ListValue {

    private int Id_service;
    private String Car;
    private String Work;
    private String Cost;
    private String Date_visit;
    private String Mileage;

    public Services_ListValue(int id_service, String car, String work,
                              String cost, String date_visit, String mileage) {

        Id_service = id_service;
        Car = car;
        Work = work;
        Cost = cost;
        Date_visit = date_visit;
        Mileage = mileage;
    }

    public void setCar(String car) {
        Car = car;
    }

    public void setWork(String work) {
        Work = work;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public void setDate_visit(String date_visit) {
        Date_visit = date_visit;
    }

    public void setMileage(String mileage) {
        Mileage = mileage;
    }

    public int getId() {
        return Id_service;
    }

    public String getCar() {
        return Car;
    }

    public String getWork() {
        return Work;
    }

    public String getCost() {
        return Cost;
    }

    public String getDate_visit() {
        return Date_visit;
    }

    public String getMileage() {
        return Mileage;
    }
}
