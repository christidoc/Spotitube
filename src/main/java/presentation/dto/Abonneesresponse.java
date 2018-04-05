package presentation.dto;

import domain.Abonnee;

import java.util.List;

public class Abonneesresponse {
    private List<AbonneeDTO> abonnees;

    public Abonneesresponse(){}

    public Abonneesresponse(List<Abonnee> abonnees) {
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
