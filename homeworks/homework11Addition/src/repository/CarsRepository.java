package repository;

import java.util.Collection;

public interface CarsRepository {
    public Collection<String> getListCarsProperty(String colorToFind, int mileageToFind);
    public long getNumCarsInRange(int min, int max);
    public String getMinCarProperty();
    public double getAverageWithProperty(String property);
}
