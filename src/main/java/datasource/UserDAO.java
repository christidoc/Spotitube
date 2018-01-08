package datasource;

import datasource.dto.UserDTO;

import java.util.List;

/**
 * Created by Christiaan on 9-11-2017.
 */
public interface UserDAO {
    List<UserDTO> getAllUsers();
}
