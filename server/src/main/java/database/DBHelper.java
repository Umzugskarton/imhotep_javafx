package database;

import database.jdbc.JDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBHelper {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private JDBC jdbc = new JDBC();

    private Connection conn;

    // JDBC Treiber
    private String jdbcDriver = jdbc.getJdbcDriver();
    // URL zur DB
    private String jdbcUrlPrefix = jdbc.getJdbcUrlPrefix();
    private final String dbUrl = "duemmer.informatik.uni-oldenburg.de";
    private final String dbPort = ":47099";
    private final String dbTable = "/db_test";
    private final String db = jdbcUrlPrefix + dbUrl + dbPort + dbTable;

    //  DB-Daten
    private final String username = "safariman";
    private final String password = "blablabla";

    public DBHelper(){
        try {
            Class.forName(jdbcDriver);
        } catch (Exception e) {
            log.error("Beim Festlegen des Datenbanktreibers ist ein Fehler aufgetreten", e);
        }
    }

    public void close() {
        try {
            // Wenn Verbindung besteht
            if (this.onConnection()) {
                // Schließen der Verbindung
                log.info("Verbindung zur DB wurde erfolgreich beendet");
                this.conn.close();
            }
        } catch (SQLException e) {
            log.error("Beim Schließen der Verbindung zur DB ist ein Fehler aufgetreten", e);
        }
    }

    public Connection getConnection(){
        try {
            this.conn = DriverManager.getConnection(db, username, password);
            if (this.onConnection()) {
                log.info("Verbindung zur DB wurde erfolgreich hergestellt");
                return this.conn;
            }
        } catch (SQLException se) {
            log.error("Beim Öffnen der Verbindung zur DB ist ein Fehler aufgetreten", se);
        }
        return conn;
    }

    /**
     * Überprüft, ob die Verbindung zur DB besteht
     *
     * @return true, wenn Verbindung besteht
     */
    private boolean onConnection() {
        try {
            if (!this.conn.isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Es konnte nicht überprüft werden, ob eine Verbindung zur DB besteht", e);
        }

        return false;
    }

    //TODO Für später. Wenn Tabelen noch nicht angelegt sind.
    //public abstract void onCreate(JDBC db);


}
