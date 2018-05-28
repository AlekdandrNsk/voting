package restaurant.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import restaurant.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
//@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

/*
    @Autowired
    private SessionFactory sessionFactory;

    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }
*/

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<User> getAll() {
        System.out.println("hello from repo");
        return em.createQuery("SELECT u FROM User u ORDER BY u.name, u.email", User.class).getResultList();
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    //@Transactional
    @Override
    public boolean delete(int id) {

/*      User ref = em.getReference(User.class, id);
        em.remove(ref);
*/
        Query query = em.createQuery("DELETE FROM User u WHERE u.id=:id");
        return query.setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }


    //@Transactional
    @Override
    public User save(User user) {
        if (user.isNew()) {
            em.persist(user);
            return user;
        } else {
            return em.merge(user);
        }
    }

}
