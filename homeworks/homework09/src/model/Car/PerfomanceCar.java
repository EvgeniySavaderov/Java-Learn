package model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PerfomanceCar extends Car{

    List<String> addons = new ArrayList<>(); //список модификаций

    public PerfomanceCar() {
        super();
    }

    public PerfomanceCar(String brand, String model, int year, int power, int boost, int pendant, int durabilty) {
        super(brand, model, year, power, boost, pendant, durabilty);
    }

    public PerfomanceCar(String brand, String model, int year, int power, int boost, int pendant, int durabilty, List<String> addons) {
        super(brand, model, year, power, boost, pendant, durabilty);
        this.addons = addons;
    }

    public PerfomanceCar(Car car) {
        super(car.getBrand(), car.getModel(), car.getYear(), car.getPower(), car.getBoost(), car.getPendant(), car.getDurabilty());
    }

    public List<String> getAddons() {
        return addons;
    }

    public void setAddons(List<String> addons) {
        this.addons = addons;
    }

    public void addAddon(String addon) { //добавленрие модификации
        addons.add(addon);
    }

    @Override
    public int getPower() { //вывод модифицированной мощности при наличии улучшения
        return addons.contains("extraPower") ? super.getPower() * 3 / 2 : super.getPower();
    }

    @Override
    public int getPendant() { //вывод модифицированной подвески при наличии улучшения
        return addons.contains("reducedPendant") ? super.getPendant() * 3 / 4 : super.getPendant();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerfomanceCar that = (PerfomanceCar) o;
        return Objects.equals(addons, that.addons);
    }

    @Override
    public String toString() { //вывод машину с дополнениями
        StringBuilder strAddons = new StringBuilder(", upgrades=[");
        for(String addon: addons) {
            strAddons.append(addon).append(" ");
        }
        strAddons.append("]");
        return "PerfomanceCar{" +
                "brand='" + super.getBrand() + '\'' +
                ", model='" + super.getModel() + '\'' +
                ", year=" + super.getYear() +
                ", power=" + super.getPower() +
                ", boost=" + super.getBoost() +
                ", pendant=" + super.getPendant() +
                ", durabilty=" + super.getDurabilty() +
                strAddons+
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addons);
    }
}
