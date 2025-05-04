package homeworks.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PizzaBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_base_id", nullable = false)
    private Long id;

    private String name; //имя
    private Integer baseCost; //стоимость
}