package database;

public class DbConnection {
    private IDbConnection connection = null;

    public void setConnection(final IDbConnection CONNECTION) {
        connection = CONNECTION;
    }

    public DbConnection getDbconnection() {
        if (connection != null) {
            return (DbConnection) connection;
        }else
            return null;
    }
}
