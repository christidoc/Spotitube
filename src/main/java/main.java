import domain.*;
import service.AbonnementService;

import java.util.List;

/**
 * Om snel iets te proberen zonder tests te hoeven schrijven :o
 */
public class main {
    public static void main (String [] args){
        AbonnementService abonnementService = new AbonnementService();
        List<Dienst> diensten = abonnementService.getAvailableAbonnementen("vodafone");
        for(Dienst d : diensten){
            System.out.println(d.toString());
        }
    }
}
