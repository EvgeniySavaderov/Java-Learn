package test;
import repository.CarsRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        CarsRepositoryImpl carsRepository = new CarsRepositoryImpl();
        carsRepository.readAndWrite("src/data/cars.txt", "src/data/output.txt");
    }
}