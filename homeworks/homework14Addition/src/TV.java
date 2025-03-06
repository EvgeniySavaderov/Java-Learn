import java.util.Random;

public class TV implements Comparable<TV> {
    private String model; //модель
    private int sizeInches; //диагональ в дюймах
    private boolean isSmartTV; //наличие Smart TV
    private boolean isEnabled; //включен
    private int currChannel; //текущий канал
    private int volume;

    static final String[] models = {"Samsung", "Sony", "LG", "Huawei", "Philips", "BQ", "Haier"}; //набор моделей

    public TV(String model, int sizeInches, boolean isSmartTV) { //конструктор с параметрами
        this.model = model;
        this.sizeInches = sizeInches;
        this.isSmartTV = isSmartTV;
        this.isEnabled = false;
        this.currChannel = 1;
        this.volume = 0;
    }

    public TV(String model, int sizeInches, boolean isSmartTV, boolean isEnabled, int currChannel, int volume) { //конструктор с параметрами
        this.model = model;
        this.sizeInches = sizeInches;
        this.isSmartTV = isSmartTV;
        this.isEnabled = isEnabled;
        this.currChannel = currChannel;
        this.volume = volume;
    }

    public TV() { //случайный конструктор
        Random r = new Random();
        int numModel = r.nextInt(7);
        this.model = models[numModel];
        this.sizeInches = r.nextInt(60)+10;
        this.isSmartTV = r.nextBoolean();
        this.isEnabled = r.nextBoolean();
        this.currChannel = r.nextInt(100);
        this.volume= r.nextInt(100);
    }

    public void changeState() { //включение выключение
        this.isEnabled = !this.isEnabled;
    }

    //следующий, предыдущий каналы, переключение на канал
    public void nextChannel() {
        if(this.isEnabled) {
            this.currChannel++;
            if (this.currChannel == 100) {
                this.currChannel = 0;
            }
        }
    }

    public void prevChannel() {
        if(this.isEnabled) {
            this.currChannel--;
            if (this.currChannel == -1) {
                this.currChannel = 99;
            }
        }
    }

    public void goToChannel(int channel) {
        if(this.isEnabled) {
            this.currChannel = channel % 100;
        }
    }

    @Override public String toString() { // строковое представление
        String res = "model: "+this.model+" sizeInches: "+this.sizeInches+" is smart tv: "+this.isSmartTV+"\nis enabled: "+this.isEnabled;
        //if(this.isEnabled) {
            res+=" current channel: "+this.currChannel+" volume: "+this.volume;
        //}
        return res;
    }

    public boolean isAllowedVolume(int allowedVolume) { //проверка громкости
        return this.volume<=allowedVolume & this.isEnabled;
    }

    @Override public int compareTo(TV o) { //сравнение громкости
        return this.currChannel - o.currChannel;
    }

    @Override public boolean equals(Object o) { //равенство объектов
        if (this == o) return true; //проверка на идентичность классов и ссылки
        if (o == null || getClass() != o.getClass()) return false;

        TV tv2 = (TV) o;
        if(!this.model.equals(tv2.model)) return false;
        if(this.sizeInches!=tv2.sizeInches) return false;
        if(this.isSmartTV!=tv2.isSmartTV) return false;
        if(this.isEnabled!=tv2.isEnabled) return false;
        if(this.currChannel!=tv2.currChannel) return false;
        if(this.volume!=tv2.volume) return false;
        return true;
    }

    @Override public int hashCode() { //хэш-код
        int res = this.model.hashCode();
        res = 29 * res + this.sizeInches;
        res = 29 * res + (this.isSmartTV ? 1: 0);
        res = 29 * res + (this.isEnabled ? 1: 0);
        res = 29 * res + this.currChannel;
        res = 29 * res + this.volume;
        return res;
    }
}