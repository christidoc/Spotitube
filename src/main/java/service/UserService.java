package service;

import datasource.UserDAO;
import domain.User;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Christiaan on 17-1-2018.
 */
public class UserService {
    @Inject
    UserDAO mySQLUserDAO;

    public UserService(){}

    public User getUser(String userName){
        return mySQLUserDAO.getUser(userName);
    }
}
