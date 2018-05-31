package restaurant.web.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import restaurant.model.User;
import restaurant.to.UserTo;
import restaurant.util.UserUtil;

import java.util.List;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(UserTo userTo) {
        if (userTo.isNew()) {
            super.create(UserUtil.createNewFromTo(userTo));
        }
    }

    @PostMapping(value = "/{id}")
    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.enable(id, enabled);
    }
}
