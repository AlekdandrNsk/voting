package restaurant.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Dish;
import restaurant.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT DISTINCT r FROM Dish r WHERE r.date=?1")
    List<Dish> findAllByDate(LocalDate date);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish u WHERE u.id=?1")
    int delete(int id);
}
