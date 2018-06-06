package restaurant.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurant.model.Dish;
import restaurant.model.Restaurant;
import restaurant.repository.datajpa.DishRepository;
import restaurant.repository.datajpa.RestaurantRepository;

import java.util.List;

@RestController
@RequestMapping("/rest/dishes")
public class DishRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAll() {
        log.info("getAll");
        return repository.findAll();
    }
}
