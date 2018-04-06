package presentation;

import domain.Abonnee;
import domain.AbonnementStatus;
import presentation.dto.AbonneeDTO;
import presentation.dto.Abonneesresponse;
import presentation.dto.ShareRequest;
import service.AbonneeService;
import service.AbonnementService;
import service.ActiveUser;
import service.LoginService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/abonnees")
public class AbonneeAPI {
    LoginService loginService = new LoginService();
    AbonneeService abonneeService = new AbonneeService();
    AbonnementService abonnementService = new AbonnementService();

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AbonneeDTO> getAbonnees(@QueryParam("token") String token) {
        ActiveUser user = loginService.getActiveUser(token);
        if (user != null) {
            List<Abonnee> abonnees = abonneeService.getAllAbonees();
            Abonneesresponse abonneesresponse = new Abonneesresponse(abonnees);
            return abonneesresponse.getAbonnees();
        }
        return null;
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response shareAbonnement(@PathParam("id") int abonneeID,
                               @QueryParam("token") String token,
                               ShareRequest shareRequest) {
        ActiveUser user = loginService.getActiveUser(token);
        if (user != null) {
            abonnementService.shareAbonnement(user, shareRequest.getId(), abonneeID);
            return Response.ok().build();
        }
        return Response.serverError().build();
    }
}
