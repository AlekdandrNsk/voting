package restaurant.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurant.model.Dish;
import restaurant.repository.datajpa.DishRepository;

import javax.validation.Valid;
import java.net.URI;

import static restaurant.util.ValidationUtil.assureIdConsistent;
import static restaurant.util.ValidationUtil.checkNew;
import static restaurant.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(DishAdminRestController.REST_URL)
public class DishAdminRestController{
    static final String REST_URL = "/rest/admin/dishes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository repository;

    @CacheEvict(value = "dishes", allEntries = true)
    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish dish) {
        log.info("create {}", dish);
        checkNew(dish);
        repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/votes" + "/{id}")
                .buildAndExpand(dish.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(dish);
    }


    @CacheEvict(value = "dishes", allEntries = true)
    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Dish dish, @PathVariable("id") int id) {
        log.info("update {} with id={}", dish, id);
        assureIdConsistent(dish, id);
        checkNotFoundWithId(repository.save(dish), id);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete dish {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        log.info("get dish {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

}
