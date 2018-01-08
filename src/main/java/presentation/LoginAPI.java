package presentation;

import domain.User;
import presentation.dto.LoginRequest;
import presentation.dto.LoginResponse;
import service.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/login")
public class LoginAPI {
    @Inject
    LoginService loginService;

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LoginResponse login(LoginRequest loginRequest) {
        User user = loginService.getUserByLogin(loginRequest.getUser(), loginRequest.getPassword());
        if(user != null) {
            return new LoginResponse(user.getUserName(), user.getToken());
        }
        else {
            return null;
        }
    }
}
