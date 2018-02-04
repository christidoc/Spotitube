package datasource;

import domain.DomainObject;


import java.sql.*;

abstract class AbstractMapper {
    MySQLConnector mySQLConnector = new MySQLConnector();
    protected abstract String findStatement();
    protected abstract DomainObject load(ResultSet rs) throws SQLException;

    protected DomainObject abstractFind(int id) {
        Connection DB = mySQLConnector.getConnection();
        PreparedStatement findStatement = null;
        try {
            findStatement = DB.prepareStatement(findStatement());
            findStatement.setLong(1, id);
            ResultSet rs = findStatement.executeQuery();
            rs.next();
            DomainObject result = load(rs);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
