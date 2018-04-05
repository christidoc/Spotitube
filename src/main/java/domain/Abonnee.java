package domain;

import datasource.AbonneeMapper;
import datasource.AbonnementMapper;

import java.util.ArrayList;
import java.util.List;

public class Abonnee extends DomainObject{
    public static AbonneeMapper abonneeMapper = new AbonneeMapper();
    private String mail;
    private String name;
    private String password;
    private List<Abonnement> abonnements;

    public Abonnee() {
        abonnements = new ArrayList<>();
    }

    public Abonnee(int id, String mail, String name, String password) {
        super(id);
        this.mail = mail;
        this.name = name;
        this.password = password;
        abonnements = new ArrayList<>();
    }

    public static Abonnee getAbonnee(String username){
        return abonneeMapper.getAbonneeByName(username);
    }

    public static List<Abonnee> getAllAbonnees(){
        return abonneeMapper.getAllAbonnees();
    }

    public void addAbonnement(Abonnement abonnement){
        abonnements.add(abonnement);
        abonnement.insert();
    }


    @Override
    public String toString() {
        String returnString = super.toString() + " Mail: " + mail + ". Name: " + name + ". Password: " + password + ". ";
        for(Abonnement a : abonnements){
            returnString += a.toString();
        }
        return returnString;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Abonnement> getAbonnements() {
        abonnements = abonneeMapper.getAllAbonnementen(getId());
        return abonnements;
    }

    public void setAbonnements(List<Abonnement> abonnements) {
        this.abonnements = abonnements;
    }
}
