package domain;

/**
 * Created by Christiaan on 13-10-2017.
 */
public class User {
    private String userName;
    private String token;

    public User(String user, String token){
        this.userName = user;
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
