package presentation;

import domain.Abonnement;
import domain.Dienst;
import presentation.dto.*;
import service.AbonnementService;
import service.ActiveUser;
import service.LoginService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/abonnementen")
public class AbonnementAPI {
    LoginService loginService = new LoginService();
    AbonnementService abonnementService = new AbonnementService();

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Abonnementenresponse getAbonnementen(@QueryParam("token") String token) {
        ActiveUser user = loginService.getActiveUser(token);
        if(user != null) {
            List<Abonnement> abonnements = abonnementService.getAbonnementenbyUser(user);
            return new Abonnementenresponse(abonnements);
        }
        return null;
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Abonnementenresponse addAbonnement (@QueryParam("token") String token,
                                          AbonnementDTO abonnementDTO){
        ActiveUser user = loginService.getActiveUser(token);
        if(user != null) {
            abonnementService.addAbonnement(user, abonnementDTO.getId());
            List<Abonnement> abonnements = abonnementService.getAbonnementenbyUser(user);
            return new Abonnementenresponse(abonnements);
        }
        return null;
    }

    @Path("/{id}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Abonnementresponse getAbonnement (@PathParam("id") int abonnementID,
                                            @QueryParam("token") String token) {
        ActiveUser user = loginService.getActiveUser(token);
        if(user != null) {
            Abonnement abonnement = abonnementService.getAbonnementbyUser(user, abonnementID);
            return new Abonnementresponse(abonnement);
        }
        return null;
    }

    @Path("/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Abonnementresponse terminateAbonnement (@PathParam("id") int abonnementID,
                                            @QueryParam("token") String token) {
        ActiveUser user = loginService.getActiveUser(token);
        if(user != null) {
            Abonnement abonnement = abonnementService.terminateAbonnement(user, abonnementID);
            return new Abonnementresponse(abonnement);
        }
        return null;
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Abonnementresponse editAbonnement (@PathParam("id") int abonnementID,
                                            @QueryParam("token") String token,
                                            Verdubbeling verdubbeling) {
        ActiveUser user = loginService.getActiveUser(token);
        if(user != null) {
            Abonnement abonnement = abonnementService.upgradeAbonnement(user, abonnementID, verdubbeling.getVerdubbeling());
            return new Abonnementresponse(abonnement);
        }
        return null;
    }

    @Path("/all/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AbonnementDTO> getAvailableAbonnementen(@QueryParam("token") String token,
                                                         @QueryParam("filter") String filter) {
        ActiveUser user = loginService.getActiveUser(token);
        if(user != null) {
            List<Dienst> diensten = abonnementService.getAvailableAbonnementen(filter);
            AvailableAbonnementenresponse availableAbonnementenresponse = new AvailableAbonnementenresponse(diensten);
            return availableAbonnementenresponse.getAbonnementen();
        }
        return null;
    }
}
