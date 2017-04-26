package database;

public class DbHelper extends SQLOpenHelper{

    public static final String DB_NAME = "swp17i";
    public static final int DB_VERSION = 1;


    //Daten für die User-Tabelle
    public static final String USER_TABLE = "user";

    public static final String USER_COLUMN_ID = "_id";
    public static final String USER_COLUMN_FNAME = "firstname";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_PASSWD = "passwd";
    public static final String USER_COLUMN_MAIL = "mail";


    public static final String USER_SQL_CREATE =
            "CREATE TABLE " + USER_TABLE +
                    "(" + USER_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    USER_COLUMN_FNAME + " TEXT NOT NULL, " +
                    USER_COLUMN_NAME + " TEXT NOT NULL, " +
                    USER_COLUMN_PASSWD + " TEXT NOT NULL, " +
                    USER_COLUMN_MAIL + " TEXT NOT NULL );";

    public static final String USER_SQL_DROP = "DROP TABLE IF EXISTS " + USER_TABLE;

    public DbHelper(String dbname, String dbtyp, String dbhost, String dbport, int version) {
        super(dbname, dbtyp, dbhost, dbport, version);
    }


    @Override
    void onCreate(DbConnection db) {

    }

    @Override
    void onUpgrade(DbConnection db, int oldVersion, int newVersion) {

    }
}

