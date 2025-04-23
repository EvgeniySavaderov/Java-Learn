package homeworks.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class TV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String model; //модель
    private Integer sizeInches; //диагональ в дюймах
    private Boolean isSmartTV; //наличие Smart TV
    private Boolean isEnabled; //включен
    private Integer currChannel; //текущий канал
    private Integer volume;

    static final String[] models = {"Samsung", "Sony", "LG", "Huawei", "Philips", "BQ", "Haier"}; //набор моделей

    public TV(String model, Integer sizeInches, Boolean isSmartTV) { //конструктор с параметрами
        this.model = model;
        this.sizeInches = sizeInches;
        this.isSmartTV = isSmartTV;
        this.isEnabled = false;
        this.currChannel = 1;
        this.volume = 0;
    }

    public TV(String model, Integer sizeInches, Boolean isSmartTV, Boolean isEnabled, Integer currChannel, Integer volume) { //конструктор с параметрами
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
}
