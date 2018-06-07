package restaurant.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import restaurant.AuthorizedUser;
import restaurant.model.User;
import restaurant.repository.datajpa.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        System.out.println(user.getEmail() + " " + user.getPassword());
        return new AuthorizedUser(user);
    }

}