package model;

public class Car {
    private String number;
    private String model;
    private String color;
    private int mileage;
    private int cost;

    public Car(String number, String model, String color, int mileage, int cost) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.cost = cost;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return this.number + " " + this.model + " " + this.color + " " + this.mileage + " " + this.cost;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || this.getClass()!=o.getClass()) return false;

        Car car2 = (Car) o;
        if(!this.number.equals(car2.number)) return false;
        if(!this.model.equals(car2.model)) return false;
        if(!this.color.equals(car2.color)) return false;
        if(this.mileage!= car2.mileage) return false;
        if(this.cost!= car2.cost) return false;
        return true;
    }

    @Override
    public int hashCode () {
        int res = this.number.hashCode();
        res = 29 * res + this.model.hashCode();
        res = 29 * res + this.color.hashCode();
        res = 29 * res + this.mileage;
        res = 29 * res + this.cost;
        return res;
    }
}
