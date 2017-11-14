package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBController {

  private final Logger log = LoggerFactory.getLogger(getClass().getName());

  // JDBC Treiber und URL zur Datenbank
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB = "jdbc:mysql://duemmer.informatik.uni-oldenburg.de:47099/db_test";

  //  Datenbank-Daten
  private static final String USERNAME = "safariman";
  private static final String PASSWORD = "blablabla";

  // DB-Objekt
  private Connection conn = null;

  /**
   * Erstellt ein neues Datenbankcontroller-Objekt und Setzt Datenbank-Treiber
   */
  public DBController() {
    try {
      Class.forName(JDBC_DRIVER);
    } catch (Exception e) {
      log.error("Beim Festlegen des Datenbanktreibers ist ein Fehler aufgetreten", e);
    }
  }

  /**
   * Baut Verbindung zur Datenbank auf
   *
   * @return true, wenn Verbindung aufgebaut wurde und besteht
   */
  public boolean connect() {
    try {
      conn = DriverManager.getConnection(DB, USERNAME, PASSWORD);
      if (this.isConnected()) {
        log.info("Verbindung zur Datenbank wurde erfolgreich hergestellt");
        return true;
      }
    } catch (SQLException se) {
      log.error("Beim Öffnen der Verbindung zur Datenbank ist ein Fehler aufgetreten", se);
    }
    return false;
  }

  /**
   * Schliesst die Verbindung
   *
   * @return true, wenn die Verbindung geschlossen wurde und nicht mehr besteht
   */
  public boolean disconnect() {
    try {
      if (this.isConnected()) {
        conn.close();
        if (!this.isConnected()) {
          return true;
        }
      } else {
        return true;
      }
    } catch (SQLException e) {
      log.error("Beim Schließen der Verbindung zur Datenbank ist ein Fehler aufgetreten", e);
    }

    return false;
  }

  /**
   * Überprueft, ob die Verbindung zur Datenbank besteht
   *
   * @return true, wenn Verbindung besteht
   */
  public boolean isConnected() {
    try {
      if (!this.conn.isClosed()) {
        return true;
      }
    } catch (SQLException e) {
      log.error("Es konnte nicht überprüft werden, ob eine Verbindung zur Datenbank besteht", e);
    }

    return false;
  }

  /**
   * Zählt die Anzahl der Resultate einer Abfrage
   *
   * @param result ResultSet, in dem die Einträge gezählt werden sollen
   * @return Anzahl der Einträge in result
   */
  public int countResults(ResultSet result) {
    int count = 0;
    try {
      result.last(); // Zeiger auf den letzten Eintrag setzen
      count = result.getRow(); // Nummer des letzten Eintrags
      result.beforeFirst(); // Zeiger vor den ersten Eintrag zurücksetzen
    } catch (Exception e) {
      return 0;
    }
    return count;
  }

  /**
   * Prüft, ob Wert value in Spalte column in Tabelle table existiert
   *
   * @param table Zu Überprüfende Tabelle
   * @param column Spalte der Tabelle
   * @param value Wert, auf den überprüft werden soll
   * @return true, wenn Eintrag existiert
   */
  public boolean exists(String table, String column, Object value) {
    PreparedStatement stmt = null;

    try {
      // Query ausführen
      stmt = conn
          .prepareStatement("SELECT id FROM " + table + " WHERE LOWER(" + column + ") = LOWER(?)");
      stmt.setObject(1, value);
      ResultSet result = stmt.executeQuery();

      int rowCount = this.countResults(result);

      result.close();
      stmt.close();

      if (rowCount > 0) {
        return true;
      }
    } catch (SQLException e) {
      log.error("Es konnte nicht überprüft werden, ob ein Datensatz existiert", e);
    }
    return false;
  }

  /**
   * @return Aktuelle Verbindung
   */
  public Connection getConnection() {
    return this.conn;
  }
}