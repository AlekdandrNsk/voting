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
import restaurant.model.Vote;
import restaurant.repository.datajpa.UserRepository;
import restaurant.repository.datajpa.VoteRepository;
import restaurant.util.ValidationUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static restaurant.util.ValidationUtil.assureIdConsistent;
import static restaurant.util.ValidationUtil.checkNew;

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
        LocalDate requiredDate = (date == null ? LocalDate.now() : date);
        log.info("getAll votes by day {}", requiredDate);
        return repository.findAllByDate(requiredDate);
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote) {
        vote.setUser(userRepository.getOne(AuthorizedUser.id()));
        log.info("create {}", vote);
        checkNew(vote);
        repository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/votes" + "/{id}")
                .buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(vote);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Vote vote, @PathVariable("id") int id) {
        log.info("update {} with id={}", vote, id);
        assureIdConsistent(vote, id);
        vote.setUser(userRepository.getOne(AuthorizedUser.id()));
        ValidationUtil.checkExcceededTime();
        ValidationUtil.checkNotFoundWithId(repository.save(vote), id);
    }
}
