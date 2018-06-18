package restaurant.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import restaurant.AuthorizedUser;
import restaurant.model.Restaurant;
import restaurant.model.Vote;
import restaurant.repository.datajpa.UserRepository;
import restaurant.repository.datajpa.VoteRepository;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static restaurant.util.ValidationUtil.*;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    static final String REST_URL = "/rest/votes";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAllByDay(@RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate requiredDate = (date == null ? LocalDate.now() : date);
        log.info("getAll votes by day {}", requiredDate);
        return repository.findAllByDate(requiredDate);
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody Restaurant restaurant) {
        Vote createdVote = new Vote(AuthorizedUser.get().getUser(), LocalDate.now(), restaurant);
        log.info("create {}", createdVote);
        repository.save(createdVote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/votes" + "/{id}")
                .buildAndExpand(createdVote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(createdVote);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        Vote updatedVote = new Vote(id, AuthorizedUser.get().getUser(), LocalDate.now(), restaurant);
        log.info("update {} with id={}", updatedVote, id);
        assureIdConsistent(updatedVote, id);
        checkExcceededTime();
        checkNotFoundWithId(repository.save(updatedVote), id);
    }
}
