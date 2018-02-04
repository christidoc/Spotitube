package domain;

/**
 * Created by Christiaan on 13-10-2017.
 */
public class User extends DomainObject{
    private String userName;
    private String password;

    public User(){}

    public User(String user, String password){
        this.userName = user;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {this.password = password; }
}
