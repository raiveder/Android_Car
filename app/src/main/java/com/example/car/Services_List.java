package com.example.car;

import net.sourceforge.jtds.jdbc.DateTime;

public class Services_List {

    private int Id_service;
    private Integer Id_car;
    private String Work;
    private double Cost;
    private DateTime Date_visit;
    private Integer Mileage;

    public Services_List(int id_service, Integer id_car, String work,
                         double cost, DateTime date_visit, Integer mileage) {

        if (id_service != 0) {
            Id_service = id_service;
        }
        Id_car = id_car;
        Work = work;
        Cost = cost;
        Date_visit = date_visit;
        Mileage = mileage;
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

    public void setDate_visit(DateTime date_visit) {
        Date_visit = date_visit;
    }

    public void setMileage(Integer mileage) {
        Mileage = mileage;
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

    public DateTime getDate_visit() {
        return Date_visit;
    }

    public Integer getMileage() {
        return Mileage;
    }
}
