package service;

import datasource.UserDAO;
import datasource.dto.UserDTO;
import domain.Token;
import domain.User;

import javax.inject.Inject;
import java.util.List;

public class LoginService {
    @Inject
    UserDAO mySQLUserDAO;
    @Inject
    TokenService tokenService;

//    public LoginService(){
//        users = new ArrayList<User>();
//    }
//
//    public List<User> getAllUsers(){
//        return users;
//    }
//
//    public User getUserByToken(String token){
//        for (User user : users) {
//            if(user.getToken().equals(token)){
//                return user;
//            }
//        }
//        return null;
//    }

    public User getUserByLogin(String username, String password) {
        List<UserDTO> users = mySQLUserDAO.getAllUsers();
        for (UserDTO userDTO : users) {
            if (userDTO.getUsername().equals(username) && userDTO.getPassword().equals(password)) {
                //Ga maximaal 10x door totdat token uniek is.
                for (int i = 0; i < 10; i++) {
                    User user = new User(userDTO.getUsername(), tokenService.getRandomToken());
                    if (Token.addUser(user)) {
                        return user;
                    }
                }
            }
        }
        return null;
    }
}
