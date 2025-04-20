import Exceptions.*;
import Model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<String> strPersons = new ArrayList<>(); //список строк для представления лиц
        Scanner console = new Scanner(System.in).useDelimiter("\n");
        String str = console.next();
        while(!str.equals("END")) {
            strPersons.add(str);
            str = console.next();
        }

        for(String s: strPersons) {
            System.out.println(s);
            try { //запись в файл
                Person p = new Person(s);
                p.writeToFile();
                System.out.println("Успешно записано в файл");
            }
            catch (ExcessDataFormatException | ExcessNameException | InvalidDataAmountException | InvalidDateException |
                   UnknownFormatException e) { //обработка исключений
                System.out.println(e.getMessage());
            }
        }
    }
}