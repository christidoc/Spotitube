package presentation.dto;

import domain.Abonnee;

import java.util.ArrayList;
import java.util.List;

public class Abonneesresponse {
    private List<AbonneeDTO> abonnees;

    public Abonneesresponse(){
        abonnees = new ArrayList<>();
    }

    public Abonneesresponse(List<Abonnee> abonnees) {
        this.abonnees = new ArrayList<>();
        for(Abonnee a : abonnees){
            this.abonnees.add(new AbonneeDTO(a.getId(), a.getName(), a.getMail()));
        }
    }

    public List<AbonneeDTO> getAbonnees() {
        return abonnees;
    }

    public void setAbonnees(List<AbonneeDTO> abonnees) {
        this.abonnees = abonnees;
    }
}
