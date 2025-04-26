package homeworks.repository;

import homeworks.model.TV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TVRepository extends JpaRepository<TV, Long> {
    @Query("select tv from TV tv where tv.isEnabled = true and tv.volume <= :volume order by tv.volume")
    List<TV> getSilentTV(@Param("volume") Integer volume);

    @Modifying(clearAutomatically = true)
    @Query("update TV tv set tv.isEnabled = not tv.isEnabled where tv.id= :id")
    void tvChangeState(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update TV tv set tv.currChannel = mod((tv.currChannel+1), 100) where tv.id= :id and tv.isEnabled = true")
    void tvNextChannel(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update TV tv set tv.currChannel = mod((tv.currChannel+99), 100) where tv.id= :id and tv.isEnabled = true")
    void tvPrevChannel(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Query("update TV tv set tv.currChannel = mod((:channel), 100) where tv.id= :id and tv.isEnabled = true")
    void tvGoToChannel(@Param("id") Long id, @Param("channel") Integer channel);
}
