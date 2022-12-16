package com.example.car;

public class Expendables {

    private int Id_expendable;
    private String Expendable;

    public Expendables(int id_expendable, String expendable) {

        Id_expendable = id_expendable;
        Expendable = expendable;
    }

    public void setId_expendable(Integer id_expendable) {
        Id_expendable = id_expendable;
    }

    public void setExpendable(String expendable) {
        Expendable = expendable;
    }



    public int getId() {
        return Id_expendable;
    }

    public String getExpendable() {
        return Expendable;
    }
}
