package service;

import domain.*;
import presentation.dto.Verdubbeling;

import java.util.ArrayList;
import java.util.List;

public class AbonnementService{
    public List<Abonnement> getAbonnementenbyUser(ActiveUser user){
        Abonnee abonnee = Abonnee.getAbonnee(user.getUserName());
        return abonnee.getAbonnements();
    }

    public void addAbonnement(ActiveUser user, int dienstID, String aanbieder, String dienstName){
        Abonnee abonnee = Abonnee.getAbonnee(user.getUserName());
        Dienst dienst = Dienst.getDienst(dienstID);
        Abonnement abonnement = new Abonnement();
        abonnement.setAbonneeID(abonnee.getId());
        abonnement.setDienst(dienst);
        abonnement.insert();
        abonnee.addAbonnement(abonnement);
    }

    public Abonnement terminateAbonnement(ActiveUser user, int abonnementID){
        List<Abonnement> abonnementen = getAbonnementenbyUser(user) ;
        for(Abonnement a : abonnementen){
            if(a.getDienst().getId() == abonnementID){
                a.setStatus(AbonnementStatus.OPGEZEGD);
                a.update();
                return a;
            }
        }
        return null;
    }

    public Abonnement getAbonnementbyUser(ActiveUser user, int abonnementID){
        List<Abonnement> abonnementen = getAbonnementenbyUser(user);
        for(Abonnement a : abonnementen){
            if(a.getDienst().getId() == abonnementID){
                return a;
            }
        }
        return null;
    }

    public Abonnement upgradeAbonnement(ActiveUser user, int abonnementID, String verdubbeling){
        List<Abonnement> abonnementen = getAbonnementenbyUser(user) ;
        for(Abonnement a : abonnementen){
            if(a.getDienst().getId() == abonnementID){
                switch (verdubbeling) {
                    case "standaard":
                        a.setVerdubbeling(VerdubbelingStatus.STANDAARD);
                        break;
                    case "verdubbeld":
                        a.setVerdubbeling(VerdubbelingStatus.VERDUBBELD);
                        break;
                    case "niet-beschikbaar":
                        a.setVerdubbeling(VerdubbelingStatus.NIETBESCHIKBAAR);
                        break;
                }
                a.update();
                return a;
            }
        }
        return null;
    }

    public List<Dienst> getAvailableAbonnementen (String filter) {
        List<Dienst> diensten = Dienst.getAllDiensten();
        if(filter != null && !filter.isEmpty()) {
            List<Dienst> returnDiensten = new ArrayList<>();
            for (Dienst d : diensten) {
                if(d.getAanbieder().equals(filter)){
                    returnDiensten.add(d);
                }
            }
            return returnDiensten;
        }
        return diensten;
    }
}
