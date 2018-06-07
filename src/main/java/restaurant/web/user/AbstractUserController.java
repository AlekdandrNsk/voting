package restaurant.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import restaurant.model.User;
import restaurant.repository.datajpa.UserRepository;

import java.util.List;

import static restaurant.util.UserUtil.prepareToSave;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAll() {
        log.info("getAll users");
        return repository.findAll();
    }

    public User get(int id) {
        log.info("get user {}", id);
        return repository.findById(id).orElse(null);
    }

    public User create(User user) {
        log.info("create {}", user);
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        repository.save(prepareToSave(user, passwordEncoder));
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return repository.getByEmail(email);
    }

}