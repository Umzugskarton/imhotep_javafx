package database.userdata;


import database.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DBUserHelper extends DBHelper {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    public static final String TABLE_USERS = "users";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_USERS +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USERNAME + " TEXT NOT NULL, " +
                    COLUMN_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT NOT NULL);";

    public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_USERS;

    DBUserHelper(){
        super();
    }
    /**
     * Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
     * Noch nicht umgesetzt
     */
    //TODO
    public void onCreate() {

    }

    /**
     * Die onUpgrade-Methode wird aufgerufen, sobald die neue Versionsnummer höher
     * als die alte Versionsnummer ist und somit ein Upgrade notwendig wird
     * Noch nicht umgesetzt
     */
    //TODO
    public void onUpgrade() {
    }



}