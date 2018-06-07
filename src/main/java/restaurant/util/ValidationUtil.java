package restaurant.util;

import restaurant.HasId;
import restaurant.util.exception.ExccededTimeLimitToChangeVote;
import restaurant.util.exception.IllegalRequestDataException;
import restaurant.util.exception.NotFoundException;

import java.time.LocalTime;

public class ValidationUtil {

    private ValidationUtil() {
    }

    public static void checkExcceededTime() {
        LocalTime limitTime = LocalTime.of(11, 00);
        if (LocalTime.now().isAfter(limitTime)) throw new ExccededTimeLimitToChangeVote("You have exceeded the time limit to change the vote");
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(HasId bean, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalRequestDataException(bean + " must be with id=" + id);
        }
    }

}