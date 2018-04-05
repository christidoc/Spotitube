package datasource;

import domain.DomainObject;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class AbstractMapper {
    MySQLConnector mySQLConnector = new MySQLConnector();
    protected abstract String findStatement();
    protected abstract String findAllStatement();
    protected abstract String insertStatement();
    protected abstract String deleteStatement();
    protected abstract String updateStatement();
    protected abstract String findNextDatabaseIdStatement();

    protected abstract DomainObject doLoad(int id, ResultSet rs) throws SQLException;
    protected abstract List<DomainObject> doLoadAll(int id, ResultSet rs) throws  SQLException;
    protected abstract void doInsert(DomainObject subject, PreparedStatement insertStatement) throws SQLException;
    //protected abstract void doDelete(DomainObject subject, PreparedStatement insertStatement) throws SQLException;
    //protected abstract void doUpdate(DomainObject subject, PreparedStatement insertStatement) throws SQLException;

    protected Map loadedMap = new HashMap();

    protected DomainObject find(int id) {
        DomainObject result = (DomainObject) loadedMap.get(id);
        if(result != null){
            return result;
        }
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement findStatement;
        try {
            findStatement = DB.prepareStatement(findStatement());
            findStatement.setInt(1, id);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            return load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected List<DomainObject> findAll(int id) {
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement findStatement;
        try {
            findStatement = DB.prepareStatement(findAllStatement());
            ResultSet rs = findStatement.executeQuery();
            return loadAll(id, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insert(DomainObject subject) {
        PreparedStatement insertStatement;
        Connection DB = mySQLConnector.getConnection();
        try {
            insertStatement = DB.prepareStatement(insertStatement(), Statement.RETURN_GENERATED_KEYS);
            doInsert(subject, insertStatement);
            insertStatement.execute();
            ResultSet rs = insertStatement.getGeneratedKeys();
            rs.next();
            int key = rs.getInt(1);
            subject.setId(key);
            loadedMap.put(subject.getId(), subject);
            return subject.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void delete(DomainObject subject) {
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement deleteStatement;
        try {
            deleteStatement = DB.prepareStatement(deleteStatement());
            deleteStatement.setInt(1, subject.getId());
            deleteStatement.execute();
            loadedMap.remove(subject.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void update(DomainObject subject) {
//        Connection DB = mySQLConnector.getConnection();
//        PreparedStatement updateStatement;
//        try {
//            updateStatement = DB.prepareStatement(updateStatement());
//            doUpdate(subject, updateStatement);
//            updateStatement.execute();
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

    protected int findNextDatabaseId() {
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement statement;
        try{
            statement = DB.prepareStatement(findNextDatabaseIdStatement());
            ResultSet rs = statement.executeQuery();
            //get next database id moet even getest worden.
            return rs.getInt("id");

        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    protected DomainObject load(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        if (loadedMap.containsKey(id)) return (DomainObject) loadedMap.get(id);
        DomainObject result = doLoad(id, rs);
        loadedMap.put(id, result);
        return result;
    }

    protected List<DomainObject> loadAll(int id, ResultSet rs) throws SQLException {
        List<DomainObject> returnlist = new ArrayList<>();
        for(DomainObject o : doLoadAll(id, rs)){
            if(loadedMap.containsKey(o.getId())){
                returnlist.add((DomainObject) loadedMap.get(o.getId()));
            }
            else{
                returnlist.add(o);
                loadedMap.put(o.getId(), o);
            }
        }
        return returnlist;
    }
}
