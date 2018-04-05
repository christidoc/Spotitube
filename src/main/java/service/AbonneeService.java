package service;

import domain.Abonnee;

import java.util.List;

public class AbonneeService {
    public List<Abonnee> getAllAbonees(){
        return Abonnee.getAllAbonnees();
    }
}
