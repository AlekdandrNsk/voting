package restaurant.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurant.AuthorizedUser;
import restaurant.model.Dish;
import restaurant.model.Restaurant;
import restaurant.model.User;
import restaurant.model.Vote;
import restaurant.repository.UserRepository;
import restaurant.repository.datajpa.RestaurantRepository;
import restaurant.repository.datajpa.VoteRepository;
import restaurant.to.UserTo;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/votes")
public class VoteRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByDay(@RequestParam(value = "date", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("getAll");
        return repository.findAllByDate(date == null ? LocalDate.now() : date);
    }

    // create new vote
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote) {
        vote.setUser(userRepository.getOne(AuthorizedUser.id()));
        repository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/votes" + "/{id}")
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(vote);
    }
}
