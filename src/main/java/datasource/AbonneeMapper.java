package datasource;

import domain.Abonnee;
import domain.Abonnement;
import domain.DomainObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbonneeMapper  extends AbstractMapper{
    protected final String findStatement() {
        return "SELECT * FROM abonnee WHERE id = ?";
    }

    protected final String findAllStatement() {
        return "SELECT * FROM abonnee";
    }

    protected final String insertStatement() {
        return "INSERT INTO abonnee (mail, name, password) VALUES (?, ?, ?)";
    }

    protected final String updateStatement() {
        return "UPDATE abonnee SET mail = ?, name = ?, password = ? WHERE id = ?";
    }

    protected final String deleteStatement() {
        return "DELETE FROM abonnee WHERE id = ?";
    }

    protected final String findNextDatabaseIdStatement() {
        return "SHOW TABLE STATUS FROM abonnee LIKE Auto_increment";
    }

    protected DomainObject doLoad(int id, ResultSet rs) throws SQLException {
        String mail = rs.getString("mail");
        String name = rs.getString("name");
        String password = rs.getString("password");
        return new Abonnee(id, mail, name, password);
    }

    protected List<DomainObject> doLoadAll(int id, ResultSet rs) throws  SQLException{
        List<DomainObject> returnList = new ArrayList<>();
        while (rs.next()) {
            int domainObjectID = rs.getInt("id");
            if(id == domainObjectID || id == 0) {
                String mail = rs.getString("mail");
                String name = rs.getString("name");
                String password = rs.getString("password");
                returnList.add(new Abonnee(domainObjectID, mail, name, password));
            }
        }
        return returnList;
    }

    protected void doInsert (DomainObject subject, PreparedStatement insertStatement) throws SQLException {
        Abonnee abonnee = (Abonnee) subject;
        abonnee.setId(findNextDatabaseId());
        insertStatement.setString(1, abonnee.getMail());
        insertStatement.setString(2, abonnee.getName());
        insertStatement.setString(3, abonnee.getPassword());
    }

    public List<Abonnement> getAllAbonnementen(int abonneeID){
        AbonnementMapper abonnementMapper = new AbonnementMapper();
        return abonnementMapper.getAllAbonnementen(abonneeID);
    }

    public List<Abonnee> getAllAbonnees(){
        List<Abonnee> returnlist = new ArrayList<>();
        for(DomainObject o : findAll(0)){
            returnlist.add((Abonnee) o);
        }
        return returnlist;
    }

    public Abonnee getAbonneeByName(String name){
        PreparedStatement findStatement = null;
        Connection DB = mySQLConnector.getConnection();
        try {
            findStatement = DB.prepareStatement("SELECT * FROM abonnee WHERE name = ?");
            findStatement.setString(1, name);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            String mail = rs.getString("mail");
            String password = rs.getString("password");
            return new Abonnee(id, mail, name, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
