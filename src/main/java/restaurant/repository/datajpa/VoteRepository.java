package restaurant.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import restaurant.model.Dish;
import restaurant.model.Vote;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    List<Vote> findAll(Sort sort);
}
