package Model;

public class DateBirth {
    private int dayBirth;
    private int monthBirth;
    private int yearBirth;

    public int getYearBirth() {
        return yearBirth;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public int getDayBirth() {
        return dayBirth;
    }

    public void setDayBirth(int dayBirth) {
        this.dayBirth = dayBirth;
    }

    public int getMonthBirth() {
        return monthBirth;
    }

    public void setMonthBirth(int monthBirth) {
        this.monthBirth = monthBirth;
    }

    @Override
    public String toString() {
        return dayBirth + "." + monthBirth + "." + yearBirth;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o==null || o.getClass()!=this.getClass()) return false;

        DateBirth dateBirth2 = (DateBirth) o;
        if(dayBirth!=dateBirth2.getDayBirth()) return false;
        if(monthBirth!=dateBirth2.getMonthBirth()) return false;
        if(yearBirth!=dateBirth2.getYearBirth()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int res = dayBirth;
        res = 31 * res + monthBirth;
        res = 31 * res + yearBirth;
        return res;
    }
}
