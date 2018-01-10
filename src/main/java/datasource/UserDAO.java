package datasource;

import domain.User;

import java.util.List;

/**
 * Created by Christiaan on 9-11-2017.
 */
public interface UserDAO {
    List<User> getAllUsers();
}
