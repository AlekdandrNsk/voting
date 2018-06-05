package restaurant.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.User;
import restaurant.repository.UserRepository;
import restaurant.to.UserTo;
import restaurant.util.UserUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static restaurant.util.UserUtil.prepareToSave;
import static restaurant.util.UserUtil.updateFromTo;


public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public void update(UserTo userTo, int id) {
        log.info("update {} with id={}", userTo, id);
        userTo.setId(id);
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return repository.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        log.info((enabled ? "enable " : "disable ") + id);
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }
}