package service;

/**
 * Created by Christiaan on 16-1-2018.
 */
public class ActiveUser {
    private String userName;
    private String token;

    public ActiveUser(){}

    public ActiveUser(String userName, String token){
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
