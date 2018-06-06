package restaurant.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restaurant.model.Restaurant;
import restaurant.model.Vote;
import restaurant.repository.datajpa.RestaurantRepository;
import restaurant.repository.datajpa.VoteRepository;

import java.util.List;

@RestController
@RequestMapping("/rest/votes")
public class VoteRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll() {
        log.info("getAll");
        return repository.findAll();
    }
}
