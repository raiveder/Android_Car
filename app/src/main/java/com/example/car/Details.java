package com.example.car;

public class Details {

    private int Id_detail;
    private String Detail;
    private Integer Id_part;

    public Details(int id_detail, String detail, Integer id_part) {

        Id_detail = id_detail;
        Detail = detail;
        Id_part = id_part;
    }

    public void setId_detail(Integer id_detail) {
        Id_detail = id_detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public void setId_part(Integer id_part) {
        Id_part = id_part;
    }


    public int getId() {
        return Id_part;
    }

    public String getDetail() {
        return Detail;
    }

    public Integer getId_part() {
        return Id_part;
    }
}