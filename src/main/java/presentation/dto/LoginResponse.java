package presentation.dto;

/**
 * Created by Christiaan on 13-10-2017.
 */
public class LoginResponse {
    private String user;
    private String token;

    public LoginResponse(){}

    public LoginResponse(String user, String token){
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String password) {
        this.token = password;
    }
}
