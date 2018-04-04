package domain;

import java.util.ArrayList;
import java.util.List;

public class Abonnee extends DomainObject{
    //public static AbonneeMapper abonneeMapper = new AbonneeMapper();
    private String mail;
    private String name;
    private List<Abonnement> abonnements;

    public Abonnee() {
        abonnements = new ArrayList<>();
    }






    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Abonnement> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(List<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }
}
