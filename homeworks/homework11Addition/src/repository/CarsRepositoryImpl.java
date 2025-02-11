package repository;
import model.*;

import java.io.*;
import java.util.*;

public class CarsRepositoryImpl implements CarsRepository{
    private ArrayList<Car> carCollection;

    public CarsRepositoryImpl() {
        carCollection = new ArrayList<>();
    }

    public void addCar(Car car) {
        carCollection.add(car);
    }

    public Car getCar(int num) {
        return carCollection.get(num);
    }

    public Collection<String> getListCarsProperty(String colorToFind, int mileageToFind) {
        return carCollection.stream() //Номера   автомобилей   по   цвету   или   пробегу
                .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .toList();
    }

    public long getNumCarsInRange(int min, int max) {
        return carCollection.stream() //Количество уникальных моделей в ценовом диапазоне
                .filter(car -> car.getCost()>=min && car.getCost()<=max)
                .distinct()
                .count();
    }

    public String getMinCarProperty() {
        Optional<Car> res = carCollection.stream() //Цвет автомобиля с минимальной стоимостью
                .min(Comparator.comparingInt(Car::getCost));
        return res.map(Car::getColor).orElse("-");
    }

    public double getAverageWithProperty(String property) {
        OptionalDouble num = carCollection.stream()
                .filter(car -> car.getModel().equals(property))
                .mapToInt(Car::getCost)
                .average();
        return num.orElse(0);
    }

    public void readAndWrite(String inputFileName, String outputFileName) {
        carCollection = new ArrayList<>();
        StringBuilder res = new StringBuilder(); //обработка запросов

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) { //чтение файла
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                try {
                    String[] args = line.split(" "); //считывание свойств оеъекта Car
                    String number = args[0];
                    String model = args[1];
                    String color = args[2];
                    int mileage = Integer.parseInt(args[3]);
                    int cost = Integer.parseInt(args[4]);
                    carCollection.add(new Car(number, model, color, mileage, cost)); //добавление в коллекцию
                }
                catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            while ((line = reader.readLine()) != null) { //обработка запросов
                String[] args = line.split(" ");
                try {
                    int numFunction = Integer.parseInt(args[0]);
                    switch (numFunction) {
                        case 1:
                            res.append("Номера автомобилей по цвету ")
                                    .append(args[0])
                                    .append(" или пробегу ")
                                    .append(args[1])
                                    .append(": ");
                            for (String number : getListCarsProperty(args[1], Integer.parseInt(args[2]))) {
                                res.append(number)
                                        .append(" ");
                            }
                            res.append("\n");
                            break;
                        case 2:
                            res.append("Уникальные автомобили в дипазоне от ")
                                    .append(Integer.parseInt(args[1]))
                                    .append(" до ")
                                    .append(Integer.parseInt(args[2]))
                                    .append(": ")
                                    .append(getNumCarsInRange(Integer.parseInt(args[1]), Integer.parseInt(args[2])))
                                    .append(" шт.");
                            res.append("\n");
                            break;
                        case 3:
                            res.append("Цвет автомобиля с минимальной стоимостью: ")
                                    .append(getMinCarProperty());
                            res.append("\n");
                            break;
                        case 4:
                            res.append("Средняя стоимость модели ")
                                    .append(args[1])
                                    .append(": ")
                                    .append(getAverageWithProperty(args[1]));
                            res.append("\n");
                            break;
                    }
                }
                catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //запись в файл
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
            writter.write("Автомобили в базе:\n");
            for(Car car: carCollection) {
                writter.write(car.toString()+"\n"); //запись машин
            }
            writter.write("\n");
            writter.write(res.toString()); //запись результатов запросов
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
