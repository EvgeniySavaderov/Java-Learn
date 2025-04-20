package model.Race;

import model.Car.Car;

import java.util.List;

public class DragRace extends CasualRace{

    public DragRace() {
        super();
    }

    public DragRace(int length, String route, int fund, List<Car> participants) {
        super(length, route, fund, participants);
    }

    public DragRace(int length, String route, int fund) {
        super(length, route, fund);
    }

    @Override
    public String toString() {
        return "DragRace{" +
                "length=" + super.getLength() +
                ", route='" + super.getRoute() + '\'' +
                ", fund=" + super.getFund() +
                ", participants=" + super.getParticipants() +
                '}';
    }
}
