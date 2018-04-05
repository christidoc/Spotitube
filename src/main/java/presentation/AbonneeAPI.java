package presentation;

import domain.Abonnee;
import domain.AbonnementStatus;
import presentation.dto.Abonneesresponse;
import presentation.dto.ShareRequest;
import service.AbonneeService;
import service.AbonnementService;
import service.ActiveUser;
import service.LoginService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/abonnees")
public class AbonneeAPI {
    LoginService loginService;
    AbonneeService abonneeService;
    AbonnementService abonnementService;

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Abonneesresponse getAbonnees(@QueryParam("token") String token) {
        ActiveUser user = loginService.getActiveUser(token);
        if (user != null) {
            List<Abonnee> abonnees = abonneeService.getAllAbonees();
            return new Abonneesresponse(abonnees);
        }
        return null;
    }

    @Path("/{id}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public int shareAbonnement(@PathParam("id") int abonneeID,
                               @QueryParam("token") String token,
                               ShareRequest shareRequest) {
        ActiveUser user = loginService.getActiveUser(token);
        if (user != null) {
            abonnementService.shareAbonnement(user, shareRequest.getId(), abonneeID);
            return 200;
        }
        return 0;
    }
}
