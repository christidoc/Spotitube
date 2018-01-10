package domain;

/**
 * Created by Christiaan on 13-10-2017.
 */
public class User {
    private String userName;
    private String password;
    private String token;

    public User(){}

    public User(String user, String password){
        this.userName = user;
        this.password = password;
    }

    public User(String user, String token, String password){
        this.userName = user;
        this.token = token;
        this.password = password;
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

    public String getPassword() { return password; }

    public void setPassword(String password) {this.password = password; }
}
