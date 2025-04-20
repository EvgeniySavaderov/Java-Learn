package homeworks.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_customer", nullable = false)
    private Users idCustomer;

    private LocalDate dateOrder;

    private int amount;

    private int discount;

    public Orders(Users idCustomer, LocalDate dateOrder, int amount, int discount) {
        this.idCustomer = idCustomer;
        this.dateOrder = dateOrder;
        this.amount = amount;
        this.discount = discount;
    }
}
