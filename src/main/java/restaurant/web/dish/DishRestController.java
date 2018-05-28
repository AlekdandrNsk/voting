package restaurant.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import restaurant.model.Dish;
import restaurant.repository.DishRepository;

import java.util.List;

@Controller
public class DishRestController {
    private static final Logger log = LoggerFactory.getLogger(DishRestController.class);

    private final DishRepository repository;

    @Autowired
    public DishRestController(DishRepository repository) {
        this.repository = repository;
    }

//    public Meal get(int id) {
//        int userId = AuthorizedUser.id();
//        log.info("get meal {} for user {}", id, userId);
//        return service.get(id, userId);
//    }

//    public void delete(int id) {
//        int userId = AuthorizedUser.id();
//        log.info("delete meal {} for user {}", id, userId);
//        service.delete(id, userId);
//    }

    public List<Dish> getAll() {
        log.info("getAll for user {}");
        return repository.getMENU();
    }

//    public Meal create(Meal meal) {
//        int userId = AuthorizedUser.id();
//        checkNew(meal);
//        log.info("create {} for user {}", meal, userId);
//        return service.create(meal, userId);
//    }
//
//    public void update(Meal meal, int id) {
//        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
//        log.info("update {} for user {}", meal, userId);
//        service.update(meal, userId);
//    }

}