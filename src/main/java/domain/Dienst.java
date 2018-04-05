package domain;

import datasource.DienstMapper;

import java.util.List;

public class Dienst extends DomainObject{
    public static DienstMapper dienstMapper = new DienstMapper();
    private AanbiederStatus aanbieder;
    private String naam;
    private double maandprijs;
    private double halfjaarprijs;
    private double jaarprijs;
    private boolean deelbaar;
    private boolean verdubbeling;

    public Dienst(){

    }

    public Dienst(int id, AanbiederStatus aanbieder, String naam, double maandprijs, double halfjaarprijs, double jaarprijs, boolean deelbaar, boolean verdubbeling) {
        super(id);
        this.aanbieder = aanbieder;
        this.naam = naam;
        this.maandprijs = maandprijs;
        this.halfjaarprijs = halfjaarprijs;
        this.jaarprijs = jaarprijs;
        this.deelbaar = deelbaar;
        this.verdubbeling = verdubbeling;
    }

    public static List<Dienst> getAllDiensten (){
        return dienstMapper.getAllDiensten();
    }

    public static Dienst getDienst(int id){
        return dienstMapper.getDienst(id);
    }

    public void delete(){
        dienstMapper.delete(this);
    }

    public void update(){
        dienstMapper.update(this);
    }

    public void insert(){
        dienstMapper.insert(this);
    }

    public AanbiederStatus getAanbieder() {
        return aanbieder;
    }

    public void setAanbieder(AanbiederStatus aanbieder) {
        this.aanbieder = aanbieder;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getMaandprijs() {
        return maandprijs;
    }

    public void setMaandprijs(double maandprijs) {
        this.maandprijs = maandprijs;
    }

    public double getHalfjaarprijs() {
        return halfjaarprijs;
    }

    public void setHalfjaarprijs(double halfjaarprijs) {
        this.halfjaarprijs = halfjaarprijs;
    }

    public double getJaarprijs() {
        return jaarprijs;
    }

    public void setJaarprijs(double jaarprijs) {
        this.jaarprijs = jaarprijs;
    }

    public boolean isDeelbaar() {
        return deelbaar;
    }

    public void setDeelbaar(boolean deelbaar) {
        this.deelbaar = deelbaar;
    }

    public boolean isVerdubbeling() {
        return verdubbeling;
    }

    public void setVerdubbeling(boolean verdubbeling) {
        this.verdubbeling = verdubbeling;
    }
}
