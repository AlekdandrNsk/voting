package restaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id=?1")
    int delete(int id);

    User getByEmail(String email);
}

