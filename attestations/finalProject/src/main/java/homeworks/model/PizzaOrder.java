package homeworks.model;

import jakarta.persistence.*;
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
@SQLDelete(sql = "update pizza_order set deleted = true where pizza_order_id=?")
@SQLRestriction("deleted = false")
public class PizzaOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_order_id", nullable = false)
    private Long id;

    private Integer cost; //рассчитываемая стоимость
    private List<String> size = new ArrayList<>(); //размеры пиццы
    private List<Integer> amount = new ArrayList<>(); //количество пиццы

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pizza_order_bases",
            joinColumns = @JoinColumn(name = "pizza_order_id", referencedColumnName = "pizza_order_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_base_id", referencedColumnName = "pizza_base_id"))
    @ToString.Exclude
    private List<PizzaBase> pizzaBaseList = new ArrayList<>();

    @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private boolean deleted = Boolean.FALSE;

    public PizzaOrder(Long id, Integer cost, List<String> size, List<Integer> amount, List<PizzaBase> pizzaBaseList) {
        this.id = id;
        this.cost = cost;
        this.size = size;
        this.amount = amount;
        this.pizzaBaseList = pizzaBaseList;
    }
}
