package datasource;

import domain.User;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserDAO {
    @Inject
    DBConnector mySQLConnector;
    //DBConnector mySQLConnector = new MySQLConnector();

    public List<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();

        String query = "SELECT * FROM user";
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while(resultSet.next()){
                User user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public User getUser(String userName){
        User user = new User();
        String query = "SELECT * FROM user WHERE username = '" + userName + "'";
        ResultSet resultSet = mySQLConnector.getSomethingFromDatabase(query);
        try {
            while(resultSet.next()){
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
