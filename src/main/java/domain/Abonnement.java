package domain;
import datasource.AbonnementMapper;

import java.time.LocalDate;

public class Abonnement extends DomainObject{
    public static AbonnementMapper abonnementMapper = new AbonnementMapper();
    private int abonneeID;
    private Dienst dienst;
    private LocalDate start;
    private LocalDate end;
    private VerdubbelingStatus verdubbeling;
    private int[] gedeeld;
    private LengteStatus lengte;
    private AbonnementStatus status;

    public Abonnement(){
        gedeeld = new int[2];
    }

    public Abonnement(int id, int abonneeID, Dienst dienst, LocalDate start, LocalDate end, VerdubbelingStatus verdubbeling, int[] gedeeld, LengteStatus lengte, AbonnementStatus status) {
        super(id);
        this.abonneeID = abonneeID;
        this.dienst = dienst;
        this.start = start;
        this.end = end;
        this.verdubbeling = verdubbeling;
        this.gedeeld = gedeeld;
        this.lengte = lengte;
        this.status = status;
    }

    public void insert(){
        abonnementMapper.insert(this);
    }

    public void update(){
        abonnementMapper.update(this);
    }

    @Override
    public String toString(){
        String returnString = "\n \t id: " + super.toString() + ". aboneeID: " + abonneeID + ". dienst: " + dienst.getNaam() + ". start " + start + ". end " + end + ".";
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

    public int[] getGedeeld() {
        return gedeeld;
    }

    public void setGedeeld(int[] gedeeld) {
        this.gedeeld = gedeeld;
    }

    public VerdubbelingStatus getVerdubbeling() {
        return verdubbeling;
    }

    public void setVerdubbeling(VerdubbelingStatus verdubbeling) {
        this.verdubbeling = verdubbeling;
    }

    public LengteStatus getLengte() {
        return lengte;
    }

    public void setLengte(LengteStatus lengte) {
        this.lengte = lengte;
    }

    public AbonnementStatus getStatus() {
        return status;
    }

    public void setStatus(AbonnementStatus status) {
        this.status = status;
    }
}
