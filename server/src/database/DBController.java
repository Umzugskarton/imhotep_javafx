package database;
import java.sql.*;

public class DBController {

  // JDBC Treiber und URL zur Datenbank
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB = "jdbc:mysql://duemmer.informatik.uni-oldenburg.de:47099/db_test";

  //  Datenbank-Daten
  static final String USERNAME = "safariman";
  static final String PASSWORD = "blablabla";

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
      // Handle errors for Class.forName
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
        System.out.println("Connected to database");
        return true;
      }
    } catch (SQLException se) {
      // TODO Auto-generated catch block
      se.printStackTrace();
    }

    return false;
  }

  /**
   * Schließt die Verbindung
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
   * Überprüft, ob die Verbindung zur Datenbank besteht
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
   * Zählt die Anzahl der Resultate einer Abfrage
   *
   * @param  result  ResultSet, in dem die Einträge gezählt werden soll
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
   * @param  table  Zu überprüfende Tabelle
   * @param  column  Spalte der Tabelle
   * @param  value  Wert, auf den überprüft werden soll
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
   * Führt ausschließlich SELECT Queries durch und gibt ein ResultSet zurück
   * UNGENUTZT
   *
   * @param  query  Datenbank-Query
   * @return Datensatz als ResultSet aus der Datenbank
   */
  public ResultSet executeQuery(String query) {
    try {
      Statement stmt = conn
          .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      ResultSet result = stmt.executeQuery(query);

      return result;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Führt INSERT, DELETE und UPDATE Queries durch und gibt ein ResultSet zurück
   * UNGENUTZT
   *
   * @param  query  Datenbank-Query
   * @return ResultSet mit den Daten aus der Datenbank
   */
  public int executeUpdate(String query) {
    try {
      Statement stmt = conn.createStatement();
      int result = stmt.executeUpdate(query);
      stmt.close();

      return result;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return 0;
  }

  /**
   * Beendet eine Query und das zugehörige ResultSet
   * UNGENUTZT
   *
   * @param  result  Das von executeQuery zuvor zurückgegebene ResultSet
   */
  public void closeQuery(ResultSet result) {
    try {
      result.getStatement().close();
      result.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * @return Aktuelle Verbindung
   */
  public Connection getConnection() {
    return this.conn;
  }
}