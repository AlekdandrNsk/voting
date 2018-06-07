package restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {


}
