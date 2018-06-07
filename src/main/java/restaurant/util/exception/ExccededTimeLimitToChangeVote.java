package restaurant.util.exception;

import org.springframework.lang.NonNull;

public class ExccededTimeLimitToChangeVote extends RuntimeException {
    public ExccededTimeLimitToChangeVote(@NonNull String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}