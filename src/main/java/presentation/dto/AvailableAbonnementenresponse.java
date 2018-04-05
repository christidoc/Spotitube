package presentation.dto;

import domain.Dienst;

import java.util.List;

public class AvailableAbonnementenresponse {
    private List<AbonnementDTO> abonnementen;

    public AvailableAbonnementenresponse (List<Dienst> diensten){
        for(Dienst dienst : diensten){
            abonnementen.add(new AbonnementDTO(dienst.getId(), dienst.getAanbieder(), dienst.getNaam()));
        }
    }

    public List<AbonnementDTO> getAbonnementen() {
        return abonnementen;
    }

    public void setAbonnementen(List<AbonnementDTO> abonnementen) {
        this.abonnementen = abonnementen;
    }
}
