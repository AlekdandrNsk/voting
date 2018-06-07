package restaurant.web.dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurant.model.Dish;

import java.net.URI;

@RestController
@RequestMapping("/rest/admin/dishes")
public class DishAdminRestController extends AbstractDishController{

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish) {
        super.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/votes" + "/{id}")
                .buildAndExpand(dish.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(dish);
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id) {
        super.update(dish, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        return super.get(id);
    }

}
