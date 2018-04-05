package domain;
import datasource.AbonnementMapper;

import java.time.LocalDate;

public class Abonnement extends DomainObject{
    public static AbonnementMapper abonnementMapper = new AbonnementMapper();
    private int abonneeID;
    private Dienst dienst;
    private LocalDate start;
    private LocalDate end;
    private boolean verdubbeld;
    private int[] gedeeld;
    private int prijs;
    private String status;

    public Abonnement(){
        gedeeld = new int[2];
    }

    public Abonnement(int id, int abonneeID, Dienst dienst, LocalDate start, LocalDate end, boolean verdubbeld, int[] gedeeld) {
        super(id);
        this.abonneeID = abonneeID;
        this.dienst = dienst;
        this.start = start;
        this.end = end;
        this.verdubbeld = verdubbeld;
        this.gedeeld = gedeeld;
    }

    public void insert(){
        abonnementMapper.insert(this);
    }

    public void update(){
        abonnementMapper.update(this);
    }

    @Override
    public String toString(){
        String returnString = "\n \t id: " + super.toString() + ". aboneeID: " + abonneeID + ". dienst: " + dienst.getNaam() + ". start " + start + ". end " + end + ". prijs " + prijs + ".";
        return returnString;
    }

    public int getAbonneeID() {
        return abonneeID;
    }

    public void setAbonneeID(int abonneeID) {
        abonneeID = abonneeID;
    }

    public Dienst getDienst() {
        return dienst;
    }

    public void setDienst(Dienst dienst) {
        this.dienst = dienst;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public boolean isVerdubbeld() {
        return verdubbeld;
    }

    public void setVerdubbeld(boolean verdubbeld) {
        this.verdubbeld = verdubbeld;
    }

    public int[] getGedeeld() {
        return gedeeld;
    }

    public void setGedeeld(int[] gedeeld) {
        this.gedeeld = gedeeld;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
