package model.Race;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Car.Car;

public class CasualRace {
    private int length; //длина
    private String route; //путь
    private int fund; //фонд
    private List<Car> participants = new ArrayList<>(); //участники машины

    public CasualRace() { //конструктор по умолчанию
        length = 1000;
        route = "Standard";
        fund = 1000000;
        this.participants.add(new Car());
    }

    public CasualRace(int length, String route, int fund) { //конструктор с параметрами
        this.length = length;
        this.route = route;
        this.fund = fund;
    }

    public CasualRace(int length, String route, int fund, List<Car> participants) {
        this.length = length;
        this.route = route;
        this.fund = fund;
        this.participants = participants;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getFund() {
        return fund;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public List<Car> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Car> participants) {
        this.participants = participants;
    }

    public void addParticipant(Car car) {
        participants.add(car);
    }

    @Override
    public String toString() {
        return "CasualRace{" +
                "length=" + length +
                ", route='" + route + '\'' +
                ", fund=" + fund +
                ", participants=" + participants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return false;
        if (o == null || getClass() != o.getClass()) return false;
        CasualRace that = (CasualRace) o;
        return length == that.length && fund == that.fund && Objects.equals(route, that.route) && Objects.equals(participants, that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, route, fund, participants);
    }
}
