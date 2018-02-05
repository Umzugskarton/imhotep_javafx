package database.jdbc;

abstract class JDBCHandler {

    //Instanzvariable vom Typ des Interface. Defaultverhalten
    private DB db = new DBmySQL();

    public void setDb(DB db) {
        this.db = db;
    }

    public String getJdbcDriver() {
        return db.getJdbcDriver();
    }

    public String getJdbcUrlPrefix() {
        return db.getJdbcUrlPrefix();
    }
}