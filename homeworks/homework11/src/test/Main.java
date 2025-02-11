package test;

import model.Car;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("a123me","Mercedes", "White" ,0, 8300000);
        Car car2 = new Car("b873of","Volga","Black",0,673000);
        Car car3 = new Car("w487mn","Lexus","Grey",76000,900000);
        Car car4 = new Car("p987hj","Volga","Red",610,704340);
        Car car5 = new Car("c987ss","Toyota","White",254000,761000);
        Car car6 = new Car("o983op","Toyota","Black",698000,740000);
        Car car7 = new Car("p146op","BMW", "White",271000,850000);
        Car car8 = new Car("u893ii","Toyota","Purple",210900,440000);
        Car car9 = new Car("l097df","Toyota","Black",108000,780000);
        Car car10 = new Car("y876wd","Toyota","Black",160000,1000000);

        Collection<Car> carCollection = new ArrayList<>(); //список машин
        carCollection.add(car1);
        carCollection.add(car2);
        carCollection.add(car3);
        carCollection.add(car4);
        carCollection.add(car5);
        carCollection.add(car6);
        carCollection.add(car7);
        carCollection.add(car8);
        carCollection.add(car9);
        carCollection.add(car10);


        String colorToFind = "Black";
        int mileageToFind = 0;
        List<String> Collection1 = carCollection.stream() //Номера   автомобилей   по   цвету   или   пробегу
                .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .toList();
        System.out.print("Номера   автомобилей   по   цвету   или   пробегу: ");
        for(String s: Collection1) {
            System.out.print(s+" ");
        }
        System.out.println();

        int n = 700000;
        int m = 800000;
        long res2 = carCollection.stream() //Количество уникальных моделей в ценовом диапазоне
                .filter(car -> car.getCost()>=n && car.getCost()<=m)
                .distinct()
                .count();
        System.out.println("Уникальные автомобили: "+res2);

        Optional<Car> res3 = carCollection.stream() //Цвет автомобиля с минимальной стоимостью
                .min(Comparator.comparingInt(Car::getCost));
        String colorRes3 = res3.map(Car::getColor).orElse("-");
        System.out.println("Цвет автомобиля с минимальной стоимостью: "+colorRes3);

        String modelToFind = "Toyota"; //Средняя стоимость искомой модели
        OptionalDouble num4 = carCollection.stream()
                .filter(car -> car.getModel().equals(modelToFind))
                .mapToInt(Car::getCost)
                .average();
        double res4 = num4.orElse(0);
        System.out.println("Средняя стоимость модели "+modelToFind+": "+res4);

        String modelToFind2 = "Volvo"; //Средняя стоимость искомой модели
        OptionalDouble num5 = carCollection.stream()
                .filter(car -> car.getModel().equals(modelToFind2))
                .mapToInt(Car::getCost)
                .average();
        double res5 = num5.orElse(0);
        System.out.println("Средняя стоимость модели "+modelToFind2+": "+res5);
    }
}