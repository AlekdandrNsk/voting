package restaurant.web.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import restaurant.model.User;
import restaurant.to.UserTo;
import restaurant.util.UserUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping("/ajax/admin/users")
public class AdminAjaxController extends AbstractUserController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid UserTo userTo, BindingResult result) {
        if (result.hasErrors()) {
            StringJoiner joiner = new StringJoiner("<br>");
            result.getFieldErrors().forEach(
                    fe -> {
                        String msg = fe.getDefaultMessage();
                        if (!msg.startsWith(fe.getField())) {
                            msg = fe.getField() + ' ' + msg;
                        }
                        joiner.add(msg);
                    });
            return new ResponseEntity<>(joiner.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (userTo.isNew()) {
            super.create(UserUtil.createNewFromTo(userTo));
        } else {
            super.update(userTo, userTo.getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    @CacheEvict(value = "users", allEntries = true)
    @Override
    @Transactional
    public void enable(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        super.enable(id, enabled);
    }
}