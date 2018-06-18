package restaurant.util.exception;

import org.springframework.lang.NonNull;

public class RoleAdminForbiddenForUser extends RuntimeException {
    public RoleAdminForbiddenForUser(@NonNull String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}