package user;
import java.sql.*;

import org.apache.commons.codec.digest.DigestUtils;

import database.DBController;

public class UserManager {

  // Datenbankcontroller
  private DBController dbController;

  /**
   * Erstellt ein neues UserManager-Objekt
   * Stellt eine Verbindung zur DB her
   */
  public UserManager() {
    this.dbController = new DBController();
    this.dbController.connect();
  }

  /**
   * Legt einen neuen User-Eintrag in der Datenbank an, wenn weder Username noch E-Mail vergeben
   * sind
   *
   * @param   password  Passwort des Users aus Registrierung
   * @param   username  Name des Users aus Registrierung
   * @param   email     E-Mail des Users aus Registrierung
   * @return  true, wenn User erfolgreich angelegt wurde
   */
  public boolean createUser(String username, String password, String email) {
    // Wenn weder Username noch E-Mail in der Datenbank vorhanden sind
    if (!this.usernameExists(username) && !this.emailExists(email)) {
      // Passwort hashen
      String hashedPassword = DigestUtils.md5Hex(password);
      PreparedStatement stmt = null;

      try {
        // User in der Datenbank anlegen
        String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        stmt = this.dbController.getConnection().prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, hashedPassword);
        stmt.setString(3, email);
        int result = stmt.executeUpdate();

        // Wenn User erfolgreich angelegt wurde, true zur�ckgeben
        if (result == 1) {
          System.out.println("User \"" + username + "\" wurde erfolgreich angelegt.");
          return true;
        }
      } catch (SQLException se) {
        // TODO Auto-generated catch block
        se.printStackTrace();
      } finally {
        try {
          // Statement freigeben
          if (stmt != null) {
            stmt.close();
          }
        } catch (SQLException se) {
          // TODO Auto-generated catch block
          se.printStackTrace();
        }
      }
    }

    return false;
  }

  /**
   * Loescht einen User-Eintrag aus der Datenbank
   *
   * @param  username  Username des Users, der geloescht werden soll
   * @return true, wenn User geloescht wurde
   */
  public boolean deleteUser(String username) {
    // Wenn Username in der Datenbank existiert
    if (this.usernameExists(username)) {
      PreparedStatement stmt = null;
      int result = 0;

      try {
        // User aus der Datenbank l�schen
        String query = "DELETE FROM users WHERE username = ?";
        stmt = this.dbController.getConnection().prepareStatement(query);
        stmt.setString(1, username);
        result = stmt.executeUpdate();

        // Wenn User erfolgreich geloescht wurde, true zur�ckgeben
        if (result == 1) {
          System.out.println("User \"" + username + "\" wurde erfolgreich geloescht.");
          return true;
        }
      } catch (SQLException se) {
        // TODO Auto-generated catch block
        se.printStackTrace();
      } finally {
        try {
          // Statement und ResultSet freigeben
          if (stmt != null) {
            stmt.close();
          }
        } catch (SQLException se) {
          // TODO Auto-generated catch block
          se.printStackTrace();
        }
      }
    }

    return false;
  }

  /**
   * Aktualisiert einen User-Eintrag in der Datenbank
   */
  public boolean updateUser() {
    return true;
  }

  /**
   * Erstellt und returned ein User-Objekt mit Datensatz aus Datenbank, wenn Username existiert
   *
   * @return User-Objekt mit Daten aus der Datenbank oder Null-Objekt, falls kein User mit username
   * gefunden wird
   * @param  username  Username
   */
  public User getUserByUsername(String username) {
    PreparedStatement stmt = null;
    ResultSet result = null;

    try {
      // Query vorbereiten und ausf�hren
      String query = "SELECT id, username, password, email FROM users WHERE username = ? LIMIT 0,1";
      stmt = this.dbController.getConnection().prepareStatement(query);
      stmt.setString(1, username);
      result = stmt.executeQuery();

      // Wenn result einen Eintrag hat (also der User existiert)
      if (this.dbController.countResults(result) == 1) {
        while (result.next()) {
          // Neues User-Objekt erstellen
          User user = new User(
              result.getInt("id"),
              result.getString("username"),
              result.getString("password"),
              result.getString("email")
          );
          // User-Objekt zur�ckgeben
          return user;
        }
      }
    } catch (SQLException se) {
      // TODO Auto-generated catch block
      se.printStackTrace();
    } finally {
      try {
        // Statement und ResultSet freigeben
        if (stmt != null) {
          stmt.close();
        }
        if (result != null) {
          result.close();
        }
      } catch (SQLException se) {
        // TODO Auto-generated catch block
        se.printStackTrace();
      }
    }
    return null;
  }

  /**
   * Erstellt und returned ein User-Objekt mit Datensatz aus Datenbank, wenn ID existiert
   *
   * @return User-Objekt mit Daten aus der Datenbank oder Null-Objekt, falls kein User mit username gefunden wird
   * @param  id   ID
   */
  public User getUserById(int id) {
    PreparedStatement stmt = null;
    ResultSet result = null;

    try {
      // Query vorbereiten und ausf�hren
      String query = "SELECT id, username, password, email FROM users WHERE id = ? LIMIT 0,1";
      stmt = this.dbController.getConnection().prepareStatement(query);
      stmt.setString(1, Integer.toString(id));
      result = stmt.executeQuery();

      // Wenn result einen Eintrag hat (also der User existiert)
      if (this.dbController.countResults(result) == 1) {
        while (result.next()) {
          // Neues User-Objekt erstellen
          User user = new User(
              result.getInt("id"),
              result.getString("username"),
              result.getString("password"),
              result.getString("email")
          );
          // User-Objekt zur�ckgeben
          return user;
        }
      }
    } catch (SQLException se) {
      // TODO Auto-generated catch block
      se.printStackTrace();
    } finally {
      try {
        // Statement und ResultSet freigeben
        if (stmt != null) {
          stmt.close();
        }
        if (result != null) {
          result.close();
        }
      } catch (SQLException se) {
        // TODO Auto-generated catch block
        se.printStackTrace();
      }
    }
    return null;
  }

  /**
   * Validiert die Eingaben eines Users fuer Login
   *
   * @param  username  Username
   * @param  password  Passwort
   * @return true, wenn Logindaten korrekt sind
   */
  public boolean validateLogin(String username, String password) {
    // Passwort hashen
    String hashedPassword = DigestUtils.md5Hex(password);

    PreparedStatement stmt = null;
    ResultSet result = null;

    try {
      // Pr�fen, ob Username und gehashtes PW in Datenbank existieren
      String query = "SELECT id, username, password, email FROM users WHERE username = ? AND password = ? LIMIT 0,1";
      stmt = this.dbController.getConnection().prepareStatement(query);
      stmt.setString(1, username);
      stmt.setString(2, hashedPassword);
      result = stmt.executeQuery();

      // Anzahl Eintr�ge mit username und gehashtem Passwort
      int rowCount = this.dbController.countResults(result);

      // Wenn result einen Eintrag hat, also Logindaten stimmen, true zur�ckgeben, sonst false
      if (rowCount == 1) {
        return true;
      }
    } catch (SQLException se) {
      // TODO Auto-generated catch block
      se.printStackTrace();
    } finally {
      try {
        // Statement und ResultSet freigeben
        if (stmt != null) {
          stmt.close();
        }
        if (result != null) {
          result.close();
        }
      } catch (SQLException se) {
        // TODO Auto-generated catch block
        se.printStackTrace();
      }
    }

    return false;
  }


  /**
   * Prueft, ob ein Username bereits in der Datenbank existiert
   *
   * @return true, wenn Username existiert
   */
  private boolean usernameExists(String username) {
    return this.dbController.exists("users", "username", username);
  }

  /**
   * Prueft, ob eine E-Mail bereits in der Datenbank existiert
   *
   * @return true, wenn E-Mail existiert
   */
  private boolean emailExists(String email) {
    return this.dbController.exists("users", "email", email);
  }
}