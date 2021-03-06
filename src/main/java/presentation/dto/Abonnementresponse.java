package presentation.dto;

import domain.Abonnement;
import domain.AbonnementStatus;
import domain.Dienst;
import domain.VerdubbelingStatus;

import java.time.LocalDate;

public class Abonnementresponse {
    private int id;
    private String aanbieder;
    private String dienst;
    private String prijs;
    private String verdubbeling;
    private boolean deelbaar;
    private String status;

    public Abonnementresponse(){}

    public Abonnementresponse(Abonnement abonnement, Dienst dienst){
        id = dienst.getId();
        aanbieder = dienst.getAanbieder().getName();
        this.dienst = dienst.getNaam();
        if(abonnement.getVerdubbeling() == VerdubbelingStatus.VERDUBBELD){
            prijs = Double.toString(dienst.getMaandprijs()*1.5);
        } else {
            prijs = Double.toString(dienst.getMaandprijs());
        }
        if(LocalDate.now().isAfter(abonnement.getEnd()) && abonnement.getStatus() == AbonnementStatus.OPGEZEGD){
            prijs = Double.toString(0);
        }
        verdubbeling = abonnement.getVerdubbeling().getName();
        deelbaar = dienst.isDeelbaar();
        status = abonnement.getStatus().getName();
    }

    public Abonnementresponse(Abonnement abonnement){
        id = abonnement.getDienst().getId();
        aanbieder = abonnement.getDienst().getAanbieder().getName();
        this.dienst = abonnement.getDienst().getNaam();
        if(abonnement.getVerdubbeling() == VerdubbelingStatus.VERDUBBELD){
            prijs = Double.toString(abonnement.getDienst().getMaandprijs()*1.5);
        } else {
            prijs = Double.toString(abonnement.getDienst().getMaandprijs());
        }
        if(LocalDate.now().isAfter(abonnement.getEnd()) && abonnement.getStatus() == AbonnementStatus.OPGEZEGD){
            prijs = Double.toString(0);
        }
        verdubbeling = abonnement.getVerdubbeling().getName();
        deelbaar = abonnement.getDienst().isDeelbaar();
        status = abonnement.getStatus().getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAanbieder() {
        return aanbieder;
    }

    public void setAanbieder(String aanbieder) {
        this.aanbieder = aanbieder;
    }

    public String getDienst() {
        return dienst;
    }

    public void setDienst(String dienst) {
        this.dienst = dienst;
    }

    public String getPrijs() {
        return prijs;
    }

    public void setPrijs(String prijs) {
        this.prijs = prijs;
    }

    public String getVerdubbeling() {
        return verdubbeling;
    }

    public void setVerdubbeling(String verdubbeling) {
        this.verdubbeling = verdubbeling;
    }

    public boolean isDeelbaar() {
        return deelbaar;
    }

    public void setDeelbaar(boolean deelbaar) {
        this.deelbaar = deelbaar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
