package service;

import datasource.UserDAO;
import domain.User;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
    @Inject
    UserDAO mySQLUserDAO;
    @Inject
    TokenService tokenService;
    private static ArrayList<ActiveUser> users = new ArrayList<>();

    public LoginService(){}

    public ActiveUser getActiveUser(String token){
        for(ActiveUser user : users){
            if(user.getToken().equals(token)){
                return user;
            }
        }
        return null;
    }

    public boolean addUser(ActiveUser newUser){
        for(ActiveUser user : users){
            if(user.getToken().equals(newUser.getToken())){
                return false;
            }
        }
        users.add(newUser);
        return true;
    }

    public boolean isViableToken(String token){
        for(ActiveUser user : users){
            if(user.getToken().equals(token)){
                return true;
            }
        }
        return false;
    }

    public ActiveUser loginUser(String username, String password) {
        if(username != null && password != null) {
            List<User> users = mySQLUserDAO.getAllUsers();
            for (User user : users) {
                if (username.equals(user.getUserName()) && password.equals(user.getPassword())) {
                    for (int i = 0; i < 10; i++) {
                        String token = tokenService.getRandomToken();
                        ActiveUser activeUser = new ActiveUser(user.getUserName(), token);
                        if (addUser(activeUser)) {
                            return activeUser;
                        }
                    }
                }
            }
        }
        return null;
    }
}
