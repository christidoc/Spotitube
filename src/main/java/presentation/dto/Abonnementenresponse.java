package presentation.dto;

import domain.Abonnement;

import java.util.List;

public class Abonnementenresponse {
    private List<AbonnementDTO> abonnementen;
    private double totalPrice;

    public Abonnementenresponse (List<Abonnement> abonnements){
        for(Abonnement abonnement : abonnements){
            abonnementen.add(new AbonnementDTO(abonnement.getDienst().getId(), abonnement.getDienst().getAanbieder(), abonnement.getDienst().getNaam()));
            totalPrice += abonnement.getPrijs();
        }
    }

    public List<AbonnementDTO> getAbonnementen() {
        return abonnementen;
    }

    public void setAbonnementen(List<AbonnementDTO> abonnementen) {
        this.abonnementen = abonnementen;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
