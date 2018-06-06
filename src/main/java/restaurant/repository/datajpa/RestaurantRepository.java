package restaurant.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import restaurant.model.Restaurant;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Query("SELECT DISTINCT r FROM Dish r WHERE r.date=?1")
    List<Restaurant> findAllByDate(LocalDate date);
}
