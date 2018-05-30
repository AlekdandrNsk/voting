package restaurant.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.model.User;
import restaurant.repository.UserRepository;

import java.util.List;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        return repository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        repository.save(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return repository.getByEmail(email);
    }
}