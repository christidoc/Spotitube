package domain;

import java.util.ArrayList;

public class Token {
    static ArrayList<User> users = new ArrayList<>();

    static public User getUser(String token){
        for(User user : users){
            if(user.getToken().equals(token)){
                return user;
            }
        }
        return null;
    }

    static public boolean addUser(User newUser){
        for(User user : users){
            if(user.getToken().equals(newUser.getToken())){
                return false;
            }
        }
        users.add(newUser);
        return true;
    }

    static public boolean isViableToken(String token){
        for(User user : users){
            if(user.getToken().equals(token)){
                return true;
            }
        }
        return false;
    }
}
