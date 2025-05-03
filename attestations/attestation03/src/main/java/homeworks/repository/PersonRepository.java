package homeworks.repository;

import homeworks.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select person from Person person where fio = :fio")
    List<Person> findPersonByFio(@Param("fio") String fio);
}
