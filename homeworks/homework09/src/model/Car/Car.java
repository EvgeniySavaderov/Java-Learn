package model.Car;

import java.util.Objects;

public class Car {
    private String brand; //бренд
    private String model; //модель
    private int year; //год
    private int power; //мощность двигателя
    private int boost; //ускорение
    private int pendant; //подвеска
    private int durabilty; //долговечность

    public Car() { //конструктор по умолчанию
        brand = "Toyota";
        model = "Camry";
        year = 2020;
        power = 150;
        boost = 20;
        pendant = 15;
        durabilty = 10000;
    }

    public Car(String brand, String model, int year, int power, int boost, int pendant, int durabilty) { //конструктор с параметрами
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.power = power;
        this.boost = boost;
        this.pendant = pendant;
        this.durabilty = durabilty;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public int getPendant() {
        return pendant;
    }

    public void setPendant(int pendant) {
        this.pendant = pendant;
    }

    public int getDurabilty() {
        return durabilty;
    }

    public void setDurabilty(int durabilty) {
        this.durabilty = durabilty;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", power=" + power +
                ", boost=" + boost +
                ", pendant=" + pendant +
                ", durabilty=" + durabilty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && power == car.power && boost == car.boost && pendant == car.pendant && durabilty == car.durabilty && Objects.equals(brand, car.brand) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, power, boost, pendant, durabilty);
    }
}
