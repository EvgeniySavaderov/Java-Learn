package Model;

import Exceptions.ExcessDataFormatException;
import Exceptions.ExcessNameException;
import Exceptions.InvalidDataAmountException;
import Exceptions.UnknownFormatException;
import Patterns.DateParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class Person {
    private FullName fullName; //ФИО
    private DateBirth dateBirth; //дата рождения
    private int phoneNumber = 0; //телефон
    private String sex; //пол
    private int age = 0; //возраст

    public FullName getFullName() {
        return fullName;
    }

    public DateBirth getDateBirth() {
        return dateBirth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String strData) { //конструктор на основе входной строки
        fullName = new FullName();

        String[] data = strData.split(" ");
        int numfields = 7;
        if (data.length != numfields) {
            {
                throw new InvalidDataAmountException(data.length, numfields); //несооттвествующее количество данных
            }
        }
        else {
            String patternName = "[А-Я][а-я]*"; //форматы входных данных
            String patternDate = "\\d{2}.\\d{2}.\\d{4}";
            String patternPhone = "\\d{6,11}";
            String patternSex = "[fm]";
            String patternAge = "\\d{2}|[1-9]";
            for(String s: data) {
                if(!s.matches(patternName) && !s.matches(patternDate) && !s.matches(patternPhone) && !s.matches(patternSex) && !s.matches(patternAge)) {
                    { //неизвестный формат
                        throw new UnknownFormatException(s);
                    }
                }
                if(s.matches(patternName)) {
                    if(fullName.getName()==null)
                        fullName.setName(s);
                    else if (fullName.getLastName()==null) {
                        fullName.setLastName(s);
                    } else if (fullName.getPatronymic()==null) {
                        fullName.setPatronymic(s);
                    }
                    else { //лишнее поле имени
                        throw new ExcessNameException();
                    }
                }
                if(s.matches(patternPhone)) {
                    if(phoneNumber==0) {
                        phoneNumber = Integer.parseInt(s);
                    }
                    else { //дубликат формата поля
                        throw new ExcessDataFormatException(s);
                    }
                }
                if(s.matches(patternSex)) {
                    if(sex==null) {
                        sex = s;
                    }
                    else { //дубликат формата поля
                        throw new ExcessDataFormatException(s);
                    }
                }
                if(s.matches(patternAge)) {
                    if(age==0) {
                        age = Integer.parseInt(s);
                    }
                    else { //дубликат формата поля
                        throw new ExcessDataFormatException(s);
                    }
                }
                if(s.matches(patternDate)) {
                    if(dateBirth==null) {
                        dateBirth = new DateBirth();
                        DateParser.ParseDate(dateBirth, s);
                    }
                    else { //дубликат формата поля
                        throw new ExcessDataFormatException(s);
                    }
                }
            }
        }
    }

    public void writeToFile() { //запись в файл
        String outputFileName = fullName.getLastName()+".txt";
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName, true))) {
            writter.write(this.toString()+"\n");
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return fullName + " " + dateBirth + " " + phoneNumber + " " + sex + " " + age;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;

        Person person2 = (Person) o;
        if(!fullName.equals(person2.getFullName())) return false;
        if(!dateBirth.equals(person2.getDateBirth())) return false;
        if(phoneNumber!=person2.getPhoneNumber()) return false;
        if(!sex.equals(person2.getSex())) return false;
        if(age!=person2.getAge()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int res = fullName.hashCode();
        res = 31 * res + dateBirth.hashCode();
        res = 31 * res + phoneNumber;
        res = 31 * res + sex.hashCode();
        res = 31 * res + age;
        return res;
    }
}
