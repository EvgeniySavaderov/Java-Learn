package model.Car;

import java.util.Objects;

public class ShowCar extends Car{
    private int stars; //рейтинг

    public ShowCar(String brand, String model, int year, int power, int boost, int pendant, int durabilty, int stars) {
        super(brand, model, year, power, boost, pendant, durabilty);
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "ShowCar{" +
                "brand='" + super.getBrand() + '\'' +
                ", model='" + super.getModel() + '\'' +
                ", year=" + super.getYear() +
                ", power=" + super.getPower() +
                ", boost=" + super.getBoost() +
                ", pendant=" + super.getPendant() +
                ", durabilty=" + super.getDurabilty() +
                ", stars=" + stars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ShowCar showCar = (ShowCar) o;
        return stars == showCar.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stars);
    }
}
