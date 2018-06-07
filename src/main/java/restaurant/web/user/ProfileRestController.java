package restaurant.web.user;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import restaurant.AuthorizedUser;
import restaurant.model.User;

import javax.validation.Valid;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController extends AbstractUserController {
    static final String REST_URL = "/rest/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user) {
        super.update(user, AuthorizedUser.id());
    }

}