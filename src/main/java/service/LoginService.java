package service;

import domain.Abonnee;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
    TokenService tokenService = new TokenService();
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
        if (username != null && password != null) {
            List<Abonnee> abonnees = Abonnee.getAllAbonnees();
            for (Abonnee abonnee : abonnees) {
                if (username.equals(abonnee.getName()) && password.equals(abonnee.getPassword())) {
                    for (int i = 0; i < 10; i++) {
                        String token = tokenService.getRandomToken();
                        ActiveUser activeUser = new ActiveUser(abonnee.getName(), token);
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
