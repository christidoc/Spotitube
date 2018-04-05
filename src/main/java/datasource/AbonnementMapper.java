package datasource;

import domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        LocalDate startdatum = rs.getDate("startdatum").toLocalDate();
        LocalDate einddatum = rs.getDate("einddatum").toLocalDate();
        VerdubbelingStatus verdubbeling = null;
        switch (rs.getString("verdubbeld")) {
            case "standaard":
                verdubbeling = VerdubbelingStatus.STANDAARD;
                break;
            case "verdubbeld":
                verdubbeling = VerdubbelingStatus.VERDUBBELD;
                break;
            case "niet-beschikbaar":
                verdubbeling = VerdubbelingStatus.NIETBESCHIKBAAR;
                break;
        }
        LengteStatus lengte = null;
        switch (rs.getString("verdubbeld")) {
            case "maand":
                lengte = LengteStatus.MAAND;
                break;
            case "halfjaar":
                lengte = LengteStatus.HALFJAAR;
                break;
            case "jaar":
                lengte = LengteStatus.JAAR;
                break;
        }
        AbonnementStatus status = null;
        switch (rs.getString("verdubbeld")) {
            case "proef":
                status = AbonnementStatus.PROEF;
                break;
            case "actief":
                status = AbonnementStatus.ACTIEF;
                break;
            case "opgezegd":
                status = AbonnementStatus.OPGEZEGD;
                break;
        }
        int[] gedeeld = new int[2];
        return new Abonnement(id, abonneeID, dienst, startdatum, einddatum, verdubbeling, gedeeld, lengte, status);
    }

    protected List<DomainObject> doLoadAll(int id, ResultSet rs) throws  SQLException{
        List<DomainObject> returnList = new ArrayList<>();
        while (rs.next()) {
            if(id == rs.getInt("abonneeID") || id == 0) {
                int abonnementID = rs.getInt("id");
                Dienst dienst = Dienst.getDienst(rs.getInt("dienstID"));
                LocalDate startdatum = rs.getDate("startdatum").toLocalDate();
                LocalDate einddatum = rs.getDate("einddatum").toLocalDate();
                VerdubbelingStatus verdubbeling = null;
                switch (rs.getString("verdubbeld")) {
                    case "standaard":
                        verdubbeling = VerdubbelingStatus.STANDAARD;
                        break;
                    case "verdubbeld":
                        verdubbeling = VerdubbelingStatus.VERDUBBELD;
                        break;
                    case "niet-beschikbaar":
                        verdubbeling = VerdubbelingStatus.NIETBESCHIKBAAR;
                        break;
                }
                LengteStatus lengte = null;
                switch (rs.getString("verdubbeld")) {
                    case "maand":
                        lengte = LengteStatus.MAAND;
                        break;
                    case "halfjaar":
                        lengte = LengteStatus.HALFJAAR;
                        break;
                    case "jaar":
                        lengte = LengteStatus.JAAR;
                        break;
                }
                AbonnementStatus status = null;
                switch (rs.getString("verdubbeld")) {
                    case "proef":
                        status = AbonnementStatus.PROEF;
                        break;
                    case "actief":
                        status = AbonnementStatus.ACTIEF;
                        break;
                    case "opgezegd":
                        status = AbonnementStatus.OPGEZEGD;
                        break;
                }
                int[] gedeeld = new int[2];
                returnList.add(new Abonnement(abonnementID, id, dienst, startdatum, einddatum, verdubbeling, gedeeld, lengte, status));
            }
        }
        return returnList;
    }

    protected void doInsert (DomainObject subject, PreparedStatement insertStatement) throws SQLException {
        Abonnement abonnement = (Abonnement) subject;
        abonnement.setId(findNextDatabaseId());
        insertStatement.setInt(1, abonnement.getAbonneeID());
        insertStatement.setInt(2, abonnement.getDienst().getId());
        insertStatement.setDate(3, java.sql.Date.valueOf(abonnement.getStart()));
        insertStatement.setDate(4, java.sql.Date.valueOf(abonnement.getEnd()));
        insertStatement.setString(5, abonnement.getVerdubbeling().getName());
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
            updateStatement.setString(5, abonnement.getVerdubbeling().getName());
            updateStatement.execute();
            //Fix de gedeelden.
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Abonnement> getAllAbonnementen(int abonneeID){
        List<Abonnement> returnlist = new ArrayList<>();
        for(DomainObject o : findAll(abonneeID)){
            returnlist.add((Abonnement) o);
        }
        return returnlist;
    }

    public Abonnement getAbonnement(int id){
        return (Abonnement)find(id);
    }

    public void addGedeeld(Abonnement abonnement, int id){
        PreparedStatement insertStatement = null;
        Connection DB = mySQLConnector.getConnection();
        try{
            insertStatement = DB.prepareStatement("INSERT INTO gedeeld (abonnementID, abonneeIDd) VALUES (?, ?)");
            insertStatement.setInt(1, abonnement.getId());
            insertStatement.setInt(2, id);
            insertStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
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
