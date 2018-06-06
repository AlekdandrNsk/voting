package restaurant.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.model.Dish;
import restaurant.model.Restaurant;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Override
    List<Dish> findAll(Sort sort);
}
