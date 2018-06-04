package restaurant.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import restaurant.model.User;
import restaurant.repository.UserRepository;

import java.util.List;

@Controller
public class UserRestController {
    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    private final UserRepository repository;

    @Autowired
    public UserRestController(UserRepository repository) {
        this.repository = repository;
    }

//    public Meal get(int id) {
//        int userId = AuthorizedUser.id();
//        log.info("get meal {} for user {}", id, userId);
//        return security.get(id, userId);
//    }

//    public void delete(int id) {
//        int userId = AuthorizedUser.id();
//        log.info("delete meal {} for user {}", id, userId);
//        security.delete(id, userId);
//    }

    public List<User> getAll() {
        log.info("getAll for user {}");
        return repository.getAll();
    }

//    public Meal create(Meal meal) {
//        int userId = AuthorizedUser.id();
//        checkNew(meal);
//        log.info("create {} for user {}", meal, userId);
//        return security.create(meal, userId);
//    }
//
//    public void update(Meal meal, int id) {
//        int userId = AuthorizedUser.id();
//        assureIdConsistent(meal, id);
//        log.info("update {} for user {}", meal, userId);
//        security.update(meal, userId);
//    }

}