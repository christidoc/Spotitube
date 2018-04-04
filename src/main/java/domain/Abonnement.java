package domain;
import datasource.AbonnementMapper;

import java.util.Date;

public class Abonnement extends DomainObject{
    public static AbonnementMapper AbonnementMapper = new AbonnementMapper();
    private int abonneeID;
    private Dienst dienst;
    private Date start;
    private Date end;
    private boolean verdubbeld;
    private int[] gedeeld;

    public Abonnement(){
        gedeeld = new int[2];
    }

    public Abonnement(int id, int abonneeID, Dienst dienst, Date start, Date end, boolean verdubbeld, int[] gedeeld) {
        super(id);
        this.abonneeID = abonneeID;
        this.dienst = dienst;
        this.start = start;
        this.end = end;
        this.verdubbeld = verdubbeld;
        this.gedeeld = gedeeld;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
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
}
