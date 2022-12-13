package com.example.car;

public class ParametrsCar {

    private String[] BrandsValues;
    private String[] ModelsValues;
    private String[] GenerationsValues;
    private String[] TransmissionsValues;
    private String[] EnginesValues;
    private String[] Fuel_TypesValues;
    private String[] DrivesValues;
    private String[] BodysValues;
    private String[] ColorsValues;
    private String[] WheelsValues;

    public ParametrsCar(String[] brandsValues, String[] modelsValues, String[] generationsValues,
                        String[] transmissionsValues, String[] enginesValues,
                        String[] fuel_TypesValues, String[] drivesValues, String[] bodysValues,
                        String[] colorsValues, String[] wheelsValues) {

        BrandsValues = brandsValues;
        ModelsValues = modelsValues;
        GenerationsValues = generationsValues;
        TransmissionsValues = transmissionsValues;
        EnginesValues = enginesValues;
        Fuel_TypesValues = fuel_TypesValues;
        DrivesValues = drivesValues;
        BodysValues = bodysValues;
        ColorsValues = colorsValues;
        WheelsValues = wheelsValues;
    }

    public void setBrandsValues(String[] brands) {
        BrandsValues = brands;
    }

    public void setModelsValues(String[] models) {
        ModelsValues = models;
    }

    public void setGenerationsValues(String[] generations) {
        GenerationsValues = generations;
    }

    public void setTransmissionsValues(String[] transmissions) {
        TransmissionsValues = transmissions;
    }

    public void setEnginesValues(String[] engines) {
        EnginesValues = engines;
    }

    public void setFuel_TypesValues(String[] fuels) {
        Fuel_TypesValues = fuels;
    }

    public void setDrivesValues(String[] drives) {
        DrivesValues = drives;
    }

    public void setBodysValues(String[] bodys) {
        BodysValues = bodys;
    }

    public void setColorsValues(String[] colors) {
        ColorsValues = colors;
    }

    public void setWheelsValues(String[] wheels) {
        WheelsValues = wheels;
    }

    public String[] getBrandsValues() {
        return BrandsValues;
    }

    public String[] getModelsValues() {
        return ModelsValues;
    }

    public String[] getGenerationsValues() {
        return GenerationsValues;
    }

    public String[] getTransmissionsValues() {
        return TransmissionsValues;
    }

    public String[] getEnginesValues() {
        return EnginesValues;
    }

    public String[] getFuel_TypesValues() {
        return Fuel_TypesValues;
    }

    public String[] getDrivesValues() {
        return DrivesValues;
    }

    public String[] getBodysValues() {
        return BodysValues;
    }

    public String[] getColorsValues() {
        return ColorsValues;
    }

    public String[] getWheelsValues() {
        return WheelsValues;
    }
}