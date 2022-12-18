package com.example.car;

public class CurrentService_List {

    private int Id_service;
    private Integer Id_car;
    private String Work;
    private double Cost;
    private String Date_visit;
    private Integer Mileage;
    private String[] Details;
    private String[] Expendables;

    public CurrentService_List(int id_service, Integer id_car, String work,
                               double cost, String date_visit, Integer mileage,
                               String[] details, String[] expendables) {

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

    public void setDetails(String[] details) {
        Details = details;
    }

    public void setExpendables(String[] expendables) {
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

    public String[] getDetails() {
        return Details;
    }

    public String[] getExpendables() {
        return Expendables;
    }
}
