import java.util.Random;

public class TV {
    private String model; //модель
    private int sizeInches; //диагональ в дюймах
    private boolean isSmartTV; //наличие Smart TV
    private boolean isEnabled; //включен
    private int currChannel; //текущий канал

    static final String[] models = {"Samsung", "Sony", "LG", "Huawei", "Philips", "BQ", "Haier"}; //набор моделей

    public TV(String model, int sizeInches, boolean isSmartTV) { //конструктор с параметрами
        this.model = model;
        this.sizeInches = sizeInches;
        this.isSmartTV = isSmartTV;
        this.isEnabled = false;
        this.currChannel = 1;
    }

    public TV() { //случайный конструктор
        Random r = new Random();
        int numModel = r.nextInt(7);
        this.model = models[numModel];
        this.sizeInches = r.nextInt(60)+10;
        this.isSmartTV = r.nextBoolean();
        this.isEnabled = r.nextBoolean();
        this.currChannel = r.nextInt(100);
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
        this.currChannel = channel%100;
    }

    @Override public String toString() { // строковое представление
        String res = "model: "+this.model+" sizeInches: "+this.sizeInches+" is smart tv: "+this.isSmartTV+"\nis enabled: "+this.isEnabled;
        if(this.isEnabled) {
            res+=" current channel: "+this.currChannel;
        }
        return res;
    }
}
