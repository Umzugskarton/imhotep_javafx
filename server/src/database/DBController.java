package database;
import java.sql.*;

public class DBController {

  // JDBC Treiber und URL zur Datenbank
  private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  private static final String DB = "jdbc:mysql://duemmer.informatik.uni-oldenburg.de:47099/db_test";

  //  Datenbank-Daten
  private static final String USERNAME = "safariman";
  private static final String PASSWORD = "blablabla";

  // DB-Objekt
  private Connection conn = null;

  /**
   * Erstellt ein neues Datenbankcontroller-Objekt
   * Setzt Datenbank-Treiber
   */
  public DBController() {
    try {
      Class.forName(JDBC_DRIVER);
    } catch (Exception e) {
      e.printStackTrace();
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
        System.out.println("Verbindung zur Datenbank wurde erfolgreich hergestellt");
        return true;
      }
    } catch (SQLException se) {
      // TODO Auto-generated catch block
      se.printStackTrace();
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
      // Wenn Verbindung besteht
      if (this.isConnected()) {
        // Schließen der Verbindung
        conn.close();

        // Wenn Schließen der Verbindung erfolgreich nicht mehr besteht, true zurückgeben
        if (!this.isConnected()) {
          return true;
        }
      } else {
        // Wenn Verbindung schon geschlossen, ebenfalls true zurückgeben
        return true;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return false; // Verbindung konnte nicht geschlossen werden
  }

  /**
   * Ueberprueft, ob die Verbindung zur Datenbank besteht
   *
   * @return true, wenn Verbindung besteht
   */
  public boolean isConnected() {
    try {
      if (!this.conn.isClosed()) {
        return true;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Zaehlt die Anzahl der Resultate einer Abfrage
   *
   * @param  result  ResultSet, in dem die Eintraege gezaehlt werden soll
   * @return Anzahl der Eintraege in result
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
   * Prueft, ob Wert value in Spalte column in Tabelle table existiert
   *
   * @param  table  Zu Ueberpruefende Tabelle
   * @param  column Spalte der Tabelle
   * @param  value  Wert, auf den ueberprueft werden soll
   * @return true, wenn Eintrag existiert
   */
  public boolean exists(String table, String column, String value) {
    PreparedStatement stmt = null;

    try {
      // Query ausführen
      stmt = conn
          .prepareStatement("SELECT id FROM " + table + " WHERE LOWER(" + column + ") = LOWER(?)");
      stmt.setString(1, value);
      ResultSet result = stmt.executeQuery();

      // Ergebnisse zählen
      int rowCount = this.countResults(result);

      // Query & Ergebnis freigeben
      result.close();
      stmt.close();

      // Wenn Anzahl größer als 0 ist, true zurückgeben
      if (rowCount > 0) {
        return true;
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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