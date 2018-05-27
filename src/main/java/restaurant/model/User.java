package restaurant.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

public class User extends AbstractNamedEntity{

    private String email;

    private String password;

    private boolean enabled = true;

    private LocalDateTime registered;

    private Set<Role> roles;

    public User(String email, String password, LocalDateTime registered) {
        this.email = email;
        this.password = password;
        this.registered = registered;
    }

    public User(Integer id, String name, String email, String password, LocalDateTime registered) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.registered = registered;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
