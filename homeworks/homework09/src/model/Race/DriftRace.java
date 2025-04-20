package model.Race;

import model.Car.Car;

import java.util.List;

public class DriftRace extends CasualRace{

    public DriftRace() {
        super();
    }

    public DriftRace(int length, String route, int fund, List<Car> participants) {
        super(length, route, fund, participants);
    }

    @Override
    public String toString() {
        return "DrifRace{" +
                "length=" + super.getLength() +
                ", route='" + super.getRoute() + '\'' +
                ", fund=" + super.getFund() +
                ", participants=" + super.getParticipants() +
                '}';
    }
}
