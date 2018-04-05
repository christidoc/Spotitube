package presentation.dto;

import domain.Abonnement;
import domain.Dienst;

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
        aanbieder = dienst.getAanbieder();
        this.dienst = dienst.getNaam();
        prijs = Integer.toString(dienst.getMaandprijs());
        verdubbeling = "standaard";
        if(dienst.isVerdubbeling()){
            verdubbeling = "niet-beschikbaar";
        }
        else{
            if(abonnement.isVerdubbeld()){
                verdubbeling = "verdubbeld";
            }
        }
        deelbaar = dienst.isDeelbaar();
        status = abonnement.getStatus();
    }

    public Abonnementresponse(Abonnement abonnement){
        id = abonnement.getDienst().getId();
        aanbieder = abonnement.getDienst().getAanbieder();
        this.dienst = abonnement.getDienst().getNaam();
        prijs = Integer.toString(abonnement.getDienst().getMaandprijs());
        verdubbeling = "standaard";
        if(abonnement.getDienst().isVerdubbeling()){
            verdubbeling = "niet-beschikbaar";
        }
        else{
            if(abonnement.isVerdubbeld()){
                verdubbeling = "verdubbeld";
            }
        }
        deelbaar = abonnement.getDienst().isDeelbaar();
        status = abonnement.getStatus();
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
