package model.Garage;

import model.Car.Car;
import model.Car.PerfomanceCar;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Garage {
    List<Car> parkedCars = new ArrayList<>(); //список машин

    public Garage() {

    }

    public Garage(List<Car> cars) {
        this.parkedCars = cars;
    }

    public List<Car> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(List<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public void addCar(Car car) { //добавление машины
        parkedCars.add(car);
    }

    public void removeAt(int index) { //удаление машины
        if(parkedCars.size()>index && index>=0) {
            parkedCars.remove(index);
        }
        else {
            System.out.println("Индекс "+index+" выходит за границы массива");
        }
    }

    public void replaceAt(int index, Car car) { //замена машины
        if(parkedCars.size()>index && index>=0) {
            parkedCars.set(index, car);
        }
        else {
            System.out.println("Индекс "+index+" выходит за границы массива");
        }
    }

    public void modifyPowerAt(int index) { //установка модификации мощности на выбранной машине
        if(parkedCars.size()>index && index>=0) {
            if(! (parkedCars.get(index) instanceof PerfomanceCar)) { //подготовка модификации
                replaceAt(index, new PerfomanceCar(parkedCars.get(index)));
            }
            PerfomanceCar car = (PerfomanceCar) parkedCars.get(index);
            if(!car.getAddons().contains("extraPower")) { //установка модифации
                car.addAddon("extraPower");
            }
            else {
                System.out.println("Модификация увеличенной мощности на машине "+index+" уже установлена");
            }
        }
        else {
            System.out.println("Индекс "+index+" выходит за границы массива");
        }
    }

    public void modifyPendantAt(int index) { //установка модификации подвески на выбранной машине
        if(parkedCars.size()>index && index>=0) {
            if(! (parkedCars.get(index) instanceof PerfomanceCar)) {
                replaceAt(index, new PerfomanceCar(parkedCars.get(index)));
            }
            PerfomanceCar car = (PerfomanceCar) parkedCars.get(index);
            if(!car.getAddons().contains("reducedPendant")) {
                car.addAddon("reducedPendant");
            }
            else {
                System.out.println("Модификация уменьшенной подвески на машине "+index+" уже установлена");
            }
        }
        else {
            System.out.println("Индекс "+index+" выходит за границы массива");
        }
    }

    @Override
    public String toString() {
        return "Garage{" +
                "parkedCars=" + parkedCars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garage garage = (Garage) o;
        return Objects.equals(parkedCars, garage.parkedCars);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(parkedCars);
    }
}
