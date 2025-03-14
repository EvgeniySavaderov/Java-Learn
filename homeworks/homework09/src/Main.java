import model.Car.Car;
import model.Car.PerfomanceCar;
import model.Car.ShowCar;
import model.Garage.Garage;
import model.Race.CasualRace;
import model.Race.DragRace;

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Car("Toyota", "Camry", 2020, 150, 20, 15, 10000);
        Car car3 = new Car("Audi", "S8", 2022, 180, 15, 20, 15000);
        Car car4 = new ShowCar("Lada", "Kalina", 2018, 100, 10, 30, 8000, 3);
        PerfomanceCar car5 = new PerfomanceCar("Lada", "Priora", 2020, 120, 18, 25, 11000);
        car5.addAddon("extraPower");
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car3);
        System.out.println(car4);
        System.out.println("car1==car2 ? "+car1.equals(car2));
        System.out.println("car1==car3 ? "+car1.equals(car3));
        CasualRace casualRace1 = new CasualRace();
        CasualRace casualRace2 = new CasualRace(2000, "Circle", 1500000);
        casualRace2.addParticipant(car1);
        casualRace2.addParticipant(car2);
        DragRace dragRace = new DragRace(2500, "Linear", 2500000);
        dragRace.addParticipant(car3);
        dragRace.addParticipant(car4);
        System.out.println(casualRace1);
        System.out.println(casualRace2);
        System.out.println(dragRace);
        System.out.println("casualRace1==casualRace2 ? "+casualRace1.equals(casualRace2));
        System.out.println("casualRace1 hashcode: "+casualRace1.hashCode());
        System.out.println("casualRace2 hashcode: "+casualRace2.hashCode());
        System.out.println("dragRace hashcode: "+dragRace.hashCode());
        Garage garage = new Garage();
        garage.addCar(car3);
        garage.addCar(car4);
        garage.addCar(car5);
        System.out.println(garage);
        garage.modifyPowerAt(0);
        garage.modifyPendantAt(1);
        garage.modifyPowerAt(2);
        garage.modifyPowerAt(3);
        System.out.println(garage);
    }
}