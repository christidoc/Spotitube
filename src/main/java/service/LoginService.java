package service;

import datasource.UserDAO;
import domain.Token;
import domain.User;

import javax.inject.Inject;
import java.util.List;

public class LoginService {
    @Inject
    UserDAO mySQLUserDAO;
    @Inject
    TokenService tokenService;

    public User getUserByLogin(String username, String password) {
        if(username != null && password != null) {
            List<User> users = mySQLUserDAO.getAllUsers();
            for (User user : users) {
                if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
                    for (int i = 0; i < 10; i++) {
                        user.setToken(tokenService.getRandomToken());
                        if (Token.addUser(user)) {
                            return user;
                        }
                    }
                }
            }
        }
        return null;
    }
}
