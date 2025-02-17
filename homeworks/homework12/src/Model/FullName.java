package Model;

import java.util.Objects;

public class FullName {
    private String name;
    private String lastName;
    private String patronymic;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        return name + " " + lastName + " " + patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;

        FullName fullName2 = (FullName) o;
        if(!name.equals(fullName2.getName())) return false;
        if(!lastName.equals(fullName2.getLastName())) return false;
        if(!patronymic.equals(fullName2.getPatronymic())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int res = name.hashCode();
        res = 31 * res + lastName.hashCode();
        res = 31 * res + patronymic.hashCode();
        return res;
    }
}
