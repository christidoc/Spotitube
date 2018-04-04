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
        return "INSERT INTO abonnement (abonneeID, dienstID, startdatum, einddatum, verdubbeld) VALUES (?, ?, ?, ?, ?)";
    }

    protected final String updateStatement() {
        return "UPDATE abonnement SET abonneeID = ?, dienstID = ?, startdatum = ?, einddatum = ?, verdubbeld = ? WHERE id = ?";
    }

    protected final String deleteStatement() {
        return "DELETE FROM abonnement WHERE id = ?";
    }

    protected final String findNextDatabaseIdStatement() {
        return "";
    }

    protected DomainObject doLoad(int id, ResultSet rs) throws SQLException {
        Dienst dienst = Dienst.getDienst(rs.getInt("dienstID"));
        int abonneeID = rs.getInt("abonneeID");
        Date startdatum = rs.getDate("startdatum");
        Date einddatum = rs.getDate("einddatum");
        boolean verdubbeld = rs.getBoolean("verdubbeld");
        int[] gedeeld = new int[2];
        return new Abonnement(id, abonneeID, dienst, startdatum, einddatum, verdubbeld, gedeeld);
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
                int[] gedeeld = new int[2];
                returnList.add(new Abonnement(abonnementID, id, dienst, startdatum, einddatum, verdubbeld, gedeeld));
            }
        }
        return returnList;
    }

    protected void doInsert (DomainObject subject, PreparedStatement insertStatement) throws SQLException {
        Abonnement abonnement = (Abonnement) subject;
        abonnement.setId(findNextDatabaseId());
        insertStatement.setInt(1, abonnement.getAbonneeID());
        insertStatement.setInt(2, abonnement.getDienst().getId());
        //insertStatement.setDate(3, abonnement.getStart());
        //insertStatement.setDate(4, abonnement.getEnd());
        insertStatement.setBoolean(5, abonnement.isVerdubbeld());
    }

    public void update (Abonnement abonnement){
        PreparedStatement updateStatement = null;
        Connection DB = mySQLConnector.getConnection();
        try {
            updateStatement = DB.prepareStatement(updateStatement());
            updateStatement.setInt(1, abonnement.getAbonneeID());
            updateStatement.setInt(2, abonnement.getDienst().getId());
            //updateStatement.setDate(3, abonnement.getStart());
            //updateStatement.setDate(4, abonnement.getEnd());
            updateStatement.setBoolean(5, abonnement.isVerdubbeld());
            updateStatement.execute();
            //Fix de gedeelden.
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Abonnement> getAllAbonnementen(int aboneeID){
        List<Abonnement> returnlist = new ArrayList<>();
        for(DomainObject o : findAll(aboneeID)){
            returnlist.add((Abonnement) o);
        }
        return returnlist;
    }

    public Abonnement getAbonnement(int id){
        return (Abonnement)find(id);
    }

    private int[] getGedeeld(Abonnement abonnement, int id){
        int[] returnArray = new int[2];
        if(abonnement.getDienst().isDeelbaar()){
            returnArray[0] = 0;
            returnArray[1] = 0;
            PreparedStatement findStatement = null;
            Connection DB = mySQLConnector.getConnection();
            try {
                findStatement = DB.prepareStatement("SELECT * FROM gedeeld WHERE abonnementID = ?");
                findStatement.setInt(1, id);
                ResultSet rs = findStatement.executeQuery();
                int i = 0;
                while (rs.next()) {
                    returnArray[i] = rs.getInt("abonneeID");
                    if (i == 1) {
                        break;
                    }
                    i++;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return returnArray;
    }
}
