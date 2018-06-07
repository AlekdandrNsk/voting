package restaurant.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.Dish;
import restaurant.model.Restaurant;
import restaurant.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT DISTINCT r FROM Vote r WHERE r.date=?1")
    List<Vote> findAllByDate(LocalDate date);
}
