package datasource;

import domain.DomainObject;


import java.sql.*;
import java.util.List;

abstract class AbstractMapper {
    MySQLConnector mySQLConnector = new MySQLConnector();
    protected abstract String findStatement();
    protected abstract String findAllStatement();
    protected abstract String insertStatement();
    protected abstract String deleteStatement();
    protected abstract String updateStatement();

    protected abstract DomainObject load(ResultSet rs) throws SQLException;
    protected abstract List<DomainObject> loadAll(ResultSet rs) throws  SQLException;
    protected abstract void doInsert(DomainObject subject, PreparedStatement insertStatement) throws SQLException;
    protected abstract void doDelete(DomainObject subject, PreparedStatement insertStatement) throws SQLException;
    protected abstract void doUpdate(DomainObject subject, PreparedStatement insertStatement) throws SQLException;

    protected DomainObject abstractFind(int id) {
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement findStatement;
        try {
            findStatement = DB.prepareStatement(findStatement());
            findStatement.setLong(1, id);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            return load(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DomainObject> abstractFindAll() {
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement findStatement;
        try {
            findStatement = DB.prepareStatement(findAllStatement());
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            return loadAll(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(DomainObject subject) {
        PreparedStatement insertStatement;
        Connection DB = mySQLConnector.getConnection();
        try {
            insertStatement = DB.prepareStatement(insertStatement());
            doInsert(subject, insertStatement);
            insertStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(DomainObject subject) {
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement deleteStatement;
        try {
            deleteStatement = DB.prepareStatement(deleteStatement());
            doDelete(subject, deleteStatement);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(DomainObject subject) {
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement updateStatement;
        try {
            updateStatement = DB.prepareStatement(updateStatement());
            doUpdate(subject, updateStatement);
            updateStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
