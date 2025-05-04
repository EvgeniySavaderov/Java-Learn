package homeworks.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update person set deleted = true where person_id=?") //soft-delete
@SQLRestriction("deleted = false")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Long id;

    private String fio; //имя

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnoreProperties("person")
    private List<PizzaOrder> pizzaOrderList = new ArrayList<>(); //писок заказов

    @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private boolean deleted = Boolean.FALSE;

    public Person(Long id, String fio, List<PizzaOrder> pizzaOrderList) {
        this.id = id;
        this.fio = fio;
        this.pizzaOrderList = pizzaOrderList;
    }
}
