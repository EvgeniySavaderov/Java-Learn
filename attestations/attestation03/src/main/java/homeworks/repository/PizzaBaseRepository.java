package homeworks.repository;

import homeworks.model.Person;
import homeworks.model.PizzaBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaBaseRepository extends JpaRepository<PizzaBase, Long> {
    @Query("select pizza from PizzaBase pizza where name = :name")
    List<PizzaBase> findPizzaByName(@Param("name") String name);
}
