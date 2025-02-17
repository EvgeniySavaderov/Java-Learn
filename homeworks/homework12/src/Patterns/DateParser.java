package Patterns;
import Exceptions.InvalidDateException;
import Model.DateBirth;

import java.util.Date;

public class DateParser { //обработка строковорго представления даты
    public static void ParseDate(DateBirth date,String s) {
        String[] dateStr = s.split("\\.");
        int day = Integer.parseInt(dateStr[0]);
        int month = Integer.parseInt(dateStr[1]);
        int year = Integer.parseInt(dateStr[2]);
        if(year > 2025 || month < 1 || month > 12 || day < 1 || day > 31 //корректность
                || day == 31 && (month == 2 || month == 4 || month == 6 || month == 9 || month == 11)
                || day == 30 && month == 2
                || day == 29 && month == 2 && (year % 4  != 0 || (year % 100 == 0 && year % 400 != 0)))
        {
            throw new InvalidDateException(s);
        }
        date.setDayBirth(day);
        date.setMonthBirth(month);
        date.setYearBirth(year);
    }
}
