package datasource;

import domain.Abonnement;
import domain.Dienst;
import domain.DomainObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AbonnementMapper extends AbstractMapper {
    protected final String findStatement() {
        return "SELECT * FROM abonnement WHERE id = ?";
    }

    protected final String findAllStatement() {
        return "SELECT * FROM abonnement";
    }

    protected final String insertStatement() {
        return "INSERT INTO abonnement (abonneeID, dienstID, startdatum, einddatum, gedeeld, verdubbeld) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    protected final String updateStatement() {
        return "UPDATE abonnement SET abonneeID = ?, dienstID = ?, startdatum = ?, einddatum = ?, gedeeld = ?, verdubbeld = ? WHERE id = ?";
    }

    protected final String deleteStatement() {
        return "DELETE FROM abonnement WHERE id = ?";
    }

    protected final String findNextDatabaseIdStatement() {
        return "";
    }

    protected DomainObject doLoad(int id, ResultSet rs) throws SQLException {
        Dienst dienst = Dienst.getDienst(rs.getInt("dienstID"));
        Date startdatum = rs.getDate("startdatum");
        Date einddatum = rs.getDate("einddatum");
        boolean verdubbeld = rs.getBoolean("verdubbeld");
        int[] gedeeld = new int[2];
        if(rs.getBoolean("gedeeld")){
            gedeeld[0] = 0;
            gedeeld[1] = 0;
            PreparedStatement findStatement = null;
            Connection DB = mySQLConnector.getConnection();
            findStatement = DB.prepareStatement("SELECT * FROM gedeeld WHERE abonnementID = ?");
            findStatement.setInt(1, id);
            ResultSet rs2 = findStatement.executeQuery();
            int i = 0;
            while (rs.next()) {
                gedeeld[i] = rs2.getInt("abonneeID");
                if(i == 1){
                    break;
                }
                i++;
            }
        }
        return new Abonnement(id, dienst, startdatum, einddatum, verdubbeld, gedeeld);
    }

    protected List<DomainObject> doLoadAll(int id, ResultSet rs) throws  SQLException{
        List<DomainObject> returnList = new ArrayList<>();
        while (rs.next()) {
            if(id == rs.getInt("abonneeID") || id == 0) {
                int abonnementID = rs.getInt("id");
                Dienst dienst = Dienst.getDienst(rs.getInt("dienstID"));
                Date startdatum = rs.getDate("startdatum");
                Date einddatum = rs.getDate("einddatum");
                boolean verdubbeld = rs.getBoolean("verdubbeld");
                boolean gedeeld = rs.getBoolean("gedeeld");
                returnList.add(new Abonnement(abonnementID, dienst, startdatum, einddatum, verdubbeld, gedeeld));
            }
        }
        return returnList;
    }

    protected void doInsert (DomainObject subject, PreparedStatement insertStatement) throws SQLException {
        //insertStatement.setInt(1, subject.getId());
        Dienst dienst = (Dienst) subject;
        dienst.setId(findNextDatabaseId());
        insertStatement.setString(1, dienst.getAanbieder());
        insertStatement.setString(2, dienst.getNaam());
        insertStatement.setInt(3, dienst.getMaandprijs());
        insertStatement.setInt(4, dienst.getHalfjaarprijs());
        insertStatement.setInt(5, dienst.getJaarprijs());
        insertStatement.setBoolean(6, dienst.isDeelbaar());
        insertStatement.setBoolean(7, dienst.isVerdubbeling());
    }

    public void update (Dienst dienst){
        PreparedStatement updateStatement = null;
        Connection DB = mySQLConnector.getConnection();
        try {
            updateStatement = DB.prepareStatement(updateStatement());
            updateStatement.setString(1, dienst.getAanbieder());
            updateStatement.setString(2, dienst.getNaam());
            updateStatement.setInt(3, dienst.getMaandprijs());
            updateStatement.setInt(4, dienst.getHalfjaarprijs());
            updateStatement.setInt(5, dienst.getJaarprijs());
            updateStatement.setBoolean(6, dienst.isDeelbaar());
            updateStatement.setBoolean(7, dienst.isVerdubbeling());
            updateStatement.setInt(8, dienst.getId());
            updateStatement.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Dienst> getAllDiensten(){
        List<Dienst> returnlist = new ArrayList<>();
        for(DomainObject o : findAll()){
            returnlist.add((Dienst) o);
        }
        return returnlist;
    }

    public Dienst getDienst(int id){
        return (Dienst)find(id);
    }
}
