package datasource;

import domain.AanbiederStatus;
import domain.Dienst;
import domain.DomainObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DienstMapper extends AbstractMapper {
    protected final String findStatement() {
        return "SELECT * FROM dienst WHERE id = ?";
    }

    protected final String findAllStatement() {
        return "SELECT * FROM dienst";
    }

    protected final String insertStatement() {
        return "INSERT INTO dienst (aanbieder, naam, maandprijs, halfjaarprijs, jaarprijs, deelbaar, verdubbeling) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    protected final String updateStatement() {
        return "UPDATE dienst SET aanbieder = ?, naam = ?, maandprijs = ?, halfjaarprijs = ?, jaarprijs = ?, deelbaar = ?, verdubbeling = ? WHERE id = ?";
    }

    protected final String deleteStatement() {
        return "DELETE FROM dienst WHERE id = ?";
    }

    protected final String findNextDatabaseIdStatement() {
        return "";
    }

    protected DomainObject doLoad(int id, ResultSet rs) throws SQLException {
        AanbiederStatus aanbiederStatus = null;
        String aanbieder = rs.getString("aanbieder");
        if("ziggo".equals(aanbieder)){
            aanbiederStatus = AanbiederStatus.ZIGGO;
        } else if("vodafone".equals(aanbieder)){
            aanbiederStatus = AanbiederStatus.VODAFONE;
        }
        String naam = rs.getString("naam");
        int maandprijs = rs.getInt("maandprijs");
        int halfjaarprijs = rs.getInt("halfjaarprijs");
        int jaarprijs = rs.getInt("jaarprijs");
        boolean deelbaar = rs.getBoolean("deelbaar");
        boolean verdubbeling = rs.getBoolean("verdubbeling");
        return new Dienst(id, aanbiederStatus, naam, maandprijs, halfjaarprijs, jaarprijs, deelbaar, verdubbeling);
    }

    protected List<DomainObject> doLoadAll(int id, ResultSet rs) throws SQLException{
        List<DomainObject> returnlist = new ArrayList<>();
        while (rs.next()) {
            AanbiederStatus aanbiederStatus = null;
            String aanbieder = rs.getString("aanbieder");
            if("ziggo".equals(aanbieder)){
                aanbiederStatus = AanbiederStatus.ZIGGO;
            } else if("vodafone".equals(aanbieder)){
                aanbiederStatus = AanbiederStatus.VODAFONE;
            }
            String naam = rs.getString("naam");
            int maandprijs = rs.getInt("maandprijs");
            int halfjaarprijs = rs.getInt("halfjaarprijs");
            int jaarprijs = rs.getInt("jaarprijs");
            boolean deelbaar = rs.getBoolean("deelbaar");
            boolean verdubbeling = rs.getBoolean("verdubbeling");
            int dienstID = rs.getInt("id");
            returnlist.add(new Dienst(dienstID, aanbiederStatus, naam, maandprijs, halfjaarprijs, jaarprijs, deelbaar, verdubbeling));
        }
        return returnlist;
    }

    protected void doInsert (DomainObject subject, PreparedStatement insertStatement) throws SQLException {
        //insertStatement.setInt(1, subject.getId());
        Dienst dienst = (Dienst) subject;
        dienst.setId(findNextDatabaseId());
        insertStatement.setString(1, dienst.getAanbieder().getName());
        insertStatement.setString(2, dienst.getNaam());
        insertStatement.setDouble(3, dienst.getMaandprijs());
        insertStatement.setDouble(4, dienst.getHalfjaarprijs());
        insertStatement.setDouble(5, dienst.getJaarprijs());
        insertStatement.setBoolean(6, dienst.isDeelbaar());
        insertStatement.setBoolean(7, dienst.isVerdubbeling());
    }

    public void update (Dienst dienst){
        PreparedStatement updateStatement = null;
        Connection DB = mySQLConnector.getConnection();
        try {
            updateStatement = DB.prepareStatement(updateStatement());
            updateStatement.setString(1, dienst.getAanbieder().getName());
            updateStatement.setString(2, dienst.getNaam());
            updateStatement.setDouble(3, dienst.getMaandprijs());
            updateStatement.setDouble(4, dienst.getHalfjaarprijs());
            updateStatement.setDouble(5, dienst.getJaarprijs());
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
        for(DomainObject o : findAll(0)){
            returnlist.add((Dienst) o);
        }
        return returnlist;
    }

    public Dienst getDienst(int id){
        return (Dienst)find(id);
    }
}
