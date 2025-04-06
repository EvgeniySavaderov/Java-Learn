package homeworks.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idCustomer", cascade = CascadeType.ALL)
    private List<Orders> ordersList;

    public Users(String fio) {
        this.fio = fio;
    }
}
