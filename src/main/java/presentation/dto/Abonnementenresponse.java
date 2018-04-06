package presentation.dto;

import domain.Abonnement;
import domain.AbonnementStatus;
import domain.VerdubbelingStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Abonnementenresponse {
    private List<AbonnementDTO> abonnementen;
    private double totalPrice;

    public Abonnementenresponse(List<Abonnement> abonnements) {
        abonnementen = new ArrayList<>();
        for (Abonnement abonnement : abonnements) {
            abonnementen.add(new AbonnementDTO(abonnement.getDienst().getId(), abonnement.getDienst().getAanbieder(), abonnement.getDienst().getNaam()));
            double keer = 1;
            if (abonnement.getVerdubbeling() == VerdubbelingStatus.VERDUBBELD) {
                keer = 1.5;
            }
            if(LocalDate.now().isAfter(abonnement.getEnd()) && abonnement.getStatus() == AbonnementStatus.OPGEZEGD){
                keer = 0;
            }
            switch (abonnement.getLengte()) {
                case MAAND:
                    totalPrice += abonnement.getDienst().getMaandprijs() * keer;
                    break;
                case HALFJAAR:
                    totalPrice += abonnement.getDienst().getHalfjaarprijs() * keer;
                    break;
                case JAAR:
                    totalPrice += abonnement.getDienst().getJaarprijs() * keer;
            }
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
