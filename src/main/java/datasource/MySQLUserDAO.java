package datasource;

import datasource.dto.UserDTO;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserDAO {
    @Inject
    DBConnector mySQLConnector;
    //DBConnector mySQLConnector = new MySQLConnector();

    public List<UserDTO> getAllUsers(){
        ArrayList<UserDTO> users = new ArrayList<>();

        String query = "SELECT * FROM user";
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while(resultSet.next()){
                UserDTO user = new UserDTO();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
