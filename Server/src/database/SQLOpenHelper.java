package database;


public abstract class SQLOpenHelper {

    private String dbname;
    private int test;
    private int dbversion;
    private IDbConnection conn = null;


    SQLOpenHelper(String dbname, String dbtyp, String dbhost, String dbport, int version){
        DbConnection conn = new DbConnection();
        conn.setConnection(new DbConnectionMySQL());

        this.dbname = dbname;
        this.dbversion = version;
    }

    public void setConnection(final IDbConnection CONNECTION) {
        conn = CONNECTION;
    }

    boolean checkParam(){

    }


    void close(){

    }

    String getDatabaseName(){
        return this.dbname;
    }

    DbConnection getReadableDatabase(){
        DbConnection db = new DbConnection();
        db.open();
        return db;
    }

    DbConnection getWritableDatabase(){
        DbConnection db = new DbConnection();
        db.open();
        return db;
    }

    void onOpen(DbConnection db){
    }

    abstract void onCreate(DbConnection db);
    abstract void onUpgrade(DbConnection db, int oldVersion, int newVersion);
}
