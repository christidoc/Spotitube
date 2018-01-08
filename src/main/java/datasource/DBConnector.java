package datasource;

import java.sql.ResultSet;

/**
 * Created by Christiaan on 9-11-2017.
 */
public interface DBConnector {
    ResultSet getSomethingFromDatabase(String query);

    void updateSomethingInDatabase(String query);
}
