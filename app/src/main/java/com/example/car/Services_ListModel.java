package com.example.car;

public class Services_ListModel {

    private int Id_service;
    private Integer Id_car;
    private String Work;
    private double Cost;
    private String Date_visit;
    private Integer Mileage;
    private int[] Details;
    private int[] Expendables;

    public Services_ListModel(int id_service, Integer id_car, String work,
                              double cost, String date_visit, Integer mileage,
                              int[] details, int[] expendables) {

        if (id_service != 0) {
            Id_service = id_service;
        }
        Id_car = id_car;
        Work = work;
        Cost = cost;
        Date_visit = date_visit;
        Mileage = mileage;
        Details = details;
        Expendables = expendables;
    }

    public void setId_car(Integer id_car) {
        Id_car = id_car;
    }

    public void setWork(String work) {
        Work = work;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    public void setDate_visit(String date_visit) {
        Date_visit = date_visit;
    }

    public void setMileage(Integer mileage) {
        Mileage = mileage;
    }

    public void setDetails(int[] details) {
        Details = details;
    }

    public void setExpendables(int[] expendables) {
        Expendables = expendables;
    }

    public Integer getId() {
        return Id_service;
    }

    public Integer getId_car() {
        return Id_car;
    }

    public String getWork() {
        return Work;
    }

    public double getCost() {
        return Cost;
    }

    public String getDate_visit() {
        return Date_visit;
    }

    public Integer getMileage() {
        return Mileage;
    }

    public int[] getDetails() {
        return Details;
    }

    public int[] getExpendables() {
        return Expendables;
    }
}
