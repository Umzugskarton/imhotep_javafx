package database.userdata;

import data.user.User;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.UserIdentifier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserDataSource {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private DBUserHelper dbUserHelper;

    private String[] columns = {
            DBUserHelper.COLUMN_ID,
            DBUserHelper.COLUMN_USERNAME,
            DBUserHelper.COLUMN_PASSWORD,
            DBUserHelper.COLUMN_EMAIL
    };

    public DBUserDataSource() {
        dbUserHelper = new DBUserHelper();
    }

    /**
     * Legt einen neuen User-Eintrag in der DB an, wenn weder Username noch E-Mail vergeben
     * sind
     *
     * @param password Passwort des Users aus Registrierung
     * @param username Name des Users aus Registrierung
     * @param email    E-Mail des Users aus Registrierung
     * @return true, wenn User erfolgreich angelegt wurde
     */
    public boolean createUser(String username, String password, String email) {
        // Wenn weder Username noch E-Mail in der DB vorhanden sind
        if (!this.userExists(UserIdentifier.USERNAME, username) && !this
                .userExists(UserIdentifier.EMAIL, email)) {
            // Passwort hashen
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            PreparedStatement stmt = null;

            try {
                // User in der DB anlegen
                String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                stmt = this.dbUserHelper.getConnection().prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, hashedPassword);
                stmt.setString(3, email);
                int result = stmt.executeUpdate();

                // Wenn User erfolgreich angelegt wurde, true zur�ckgeben
                if (result == 1) {
                    log.info("User \"" + username + "\" wurde erfolgreich angelegt");
                    return true;
                }
            } catch (SQLException se) {
                log.error("Beim Anlegen eines Users in die DB ist ein Fehler aufgetreten", se);
            } finally {
                try {
                    // Statement freigeben
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException se) {
                    log.error(
                            "Beim Freigeben des Statements zum Anlegen eines Users in die DB ist ein Fehler aufgetreten",
                            se);
                }
                // Connection freigeben
                this.dbUserHelper.close();
            }
        }

        return false;
    }

    /**
     * Löscht einen User-Eintrag aus der DB
     *
     * @param identifier Ein einzigartiger Identifier (z.B. UserIdentifier.ID), anhand dessen der User ermittelt wird
     * @param value      Wert des Identifiers
     * @return true, wenn User gelöscht wurde
     */
    public boolean deleteUser(UserIdentifier identifier, Object value) {
        // Wenn User in der DB existiert
        if (this.userExists(identifier, value)) {
            PreparedStatement stmt = null;
            int result;

            try {
                // User aus der DB löschen
                String query = "DELETE FROM users WHERE " + identifier.getColumnName() + " = ?";
                stmt = this.dbUserHelper.getConnection().prepareStatement(query);
                stmt.setObject(1, value);
                result = stmt.executeUpdate();

                // Wenn User erfolgreich gelöscht wurde, true zurückgeben
                if (result == 1) {
                    log.info("User \"" + value.toString() + "\" (" + identifier.getColumnName()
                            + ") wurde erfolgreich gelöscht");
                    return true;
                }
            } catch (SQLException se) {
                log.error("Beim Löschen eines Users aus der DB ist ein Fehler aufgetreten", se);
            } finally {
                try {
                    // Statement und ResultSet freigeben
                    if (stmt != null) {
                        stmt.close();
                    }

                } catch (SQLException se) {
                    log.error(
                            "Beim Freigeben des Statements zum Löschen eines Users in die DB ist ein Fehler aufgetreten",
                            se);
                }
                dbUserHelper.close();
            }
        } else {
            log.warn("User (" + identifier.getColumnName() + ": '" + value
                    + "') konnte nicht gelöscht werden, da er nicht existiert");
        }

        return false;
    }

    /**
     * Ändert Email oder Passwort eines Users in der DB
     *
     * @param user       User-Objekt des Users, dessen Passwort geändert werden soll
     * @param identifier Ein änderbarer Identifier (z.B. UserIdentifier.EMAIL), d.h. die Spalte die geändert werden soll
     * @param value      Der neue Wert der Spalte
     * @return true, wenn User gelöscht wurde
     */
    public boolean changeUser(User user, UserIdentifier identifier, Object value) {
        // Wenn Username in der DB existiert
        int id = user.getId();

        if (this.userExists(UserIdentifier.ID, id)) {
            // Wenn das zu ändernde Objekt eine Spalte ist die nicht geändert werden darf, abbrechen
            if (!identifier.isChangeable()) {
                log.warn(identifier.getColumnName() + " des Users mit der ID " + id
                        + " konnte nicht geändert werden, da diese Spalte nicht geändert werden darf.");
                return false;
            }

            // Wenn das zu ändernde Objekt die Email ist, prüfen ob diese noch nicht vergeben ist
            if (identifier == UserIdentifier.EMAIL && userExists(UserIdentifier.EMAIL, value)) {
                log.warn(identifier.getColumnName() + " des Users mit der ID " + id
                        + " konnte nicht geändert werden, da die gewünschte E-Mail bereits benutzt wird.");
                return false;
            }

            // Wenn das zu ändernde Objekt das Passwort ist, hashen
            if (identifier == UserIdentifier.PASSWORD) {
                // Wenn der Wert ein String ist
                if (value instanceof String) {
                    value = BCrypt.hashpw((String) value, BCrypt.gensalt());
                } else {
                    log.warn("Das Passwort des Users mit der ID " + id
                            + " konnte nicht geändert werden, da das bereitgestellte Passwort kein String ist.");
                    return false;
                }
            }

            PreparedStatement stmt = null;
            int result;

            try {
                // Wert in der DB ändern
                String query = "UPDATE users SET " + identifier.getColumnName() + " = ? WHERE id = ?";
                stmt = this.dbUserHelper.getConnection().prepareStatement(query);
                stmt.setObject(1, value);
                stmt.setInt(2, id);
                result = stmt.executeUpdate();

                // Wenn Wert erfolgreich geändert wurde, true zurückgeben
                if (result == 1) {
                    log.info(
                            identifier.getColumnName() + " von User \"" + id + "\" wurde erfolgreich geändert.");
                    return true;
                }
            } catch (SQLException se) {
                log.error("Beim Ändern der Daten eines Users in der DB ist ein Fehler aufgetreten", se);
            } finally {
                try {
                    // Statement und ResultSet freigeben
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException se) {
                    log.error(
                            "Beim Freigeben des Statements zum Ändern der Daten eines Users in der DB ist ein Fehler aufgetreten",
                            se);
                }
                dbUserHelper.close();
            }
        }

        return false;
    }

    /**
     * Erstellt und returned ein User-Objekt mit Datensatz aus DB, wenn Username existiert
     *
     * @param identifier Ein einzigartiger Identifier (z.B. UserIdentifier.ID), anhand dessen der User ermittelt wird
     * @param value      Wert des Identifiers
     * @return User-Objekt mit Daten aus der DB oder Null-Objekt, falls kein User mit username
     * gefunden wird
     */
    public User getUser(UserIdentifier identifier, Object value) {
        // Zunächst überprüfen ob der User überhaupt mit diesem identifier ermittelt werden kann
        if (userExists(identifier, value)) {
            PreparedStatement stmt = null;
            ResultSet result = null;

            try {
                // Query vorbereiten und ausführen
                String query =
                        "SELECT id, username, password, email FROM users WHERE " + identifier.getColumnName()
                                + " = ? LIMIT 0,1";
                stmt = this.dbUserHelper.getConnection().prepareStatement(query);
                stmt.setObject(1, value);
                result = stmt.executeQuery();

                // Wenn result einen Eintrag hat (also der User existiert)
                if (countResults(result) == 1) {
                    result.next();

                    return new User(
                            result.getInt("id"),
                            result.getString("username"),
                            result.getString("password"),
                            result.getString("email")
                    );
                }
            } catch (SQLException se) {
                log.error("Beim Abfragen eines Users aus der DB ist ein Fehler aufgetreten", se);
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
                    log.error(
                            "Beim Freigeben des Statements zum Abfragen eines Users aus der DB ist ein Fehler aufgetreten",
                            se);
                }
                dbUserHelper.close();
            }
        } else {
            log.warn("Es konnte kein User-Objekt erstellt werden, da der gesuchte User nicht existiert");
        }
        return null;
    }

    /**
     * Validiert die Eingaben eines Users für Login
     *
     * @param username Username
     * @param password Passwort
     * @return true, wenn Logindaten korrekt sind
     */
    public boolean validateLogin(String username, String password) {
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            // Prüfen, ob Username in DB existieren
            String query = "SELECT id, username, password, email FROM users WHERE username = ? LIMIT 0,1";
            stmt = this.dbUserHelper.getConnection().prepareStatement(query);
            stmt.setString(1, username);
            result = stmt.executeQuery();

            // Anzahl Einträge mit übereinstimmendem Benutzernamen
            int rowCount = countResults(result);

            // Wenn result einen Eintrag hat (also der User existiert)
            if (countResults(result) == 1) {
                result.next();
                String passwordFromDB = result.getString("password");

                // Überprüfen, ob das gegebene Passwort mit dem gehashten übereinstimmt
                try {
                    if (BCrypt.checkpw(password, passwordFromDB)) {
                        return true;
                    } else {
                        log.debug("Passwort inkorrekt");
                    }
                } catch (IllegalArgumentException e) {
                    log.error("Beim Vergleich der Passwörter ist ein Fehler aufgetreten", e);
                }
            }
        } catch (SQLException se) {
            log.error("Bei der Validierung von Logindaten ist ein SQL-Fehler aufgetreten", se);
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
                log.error(
                        "Beim Freigeben des Statements zur Validierung von Logindaten ist ein SQL-Fehler aufgetreten",
                        se);
            }
            this.dbUserHelper.close();
        }

        return false;
    }


    /**
     * Prüft, ob ein User bereits in der DB existiert
     *
     * @param identifier Ein einzigartiger Identifier (z.B. UserIdentifier.ID), anhand dessen der User ermittelt wird
     * @param value      Wert des Identifiers
     * @return true, wenn User existiert
     */
    private boolean userExists(UserIdentifier identifier, Object value) {
        // Prüfen, ob der Identifier einzigartig ist (d.h. so dass z.B. nicht über das Passwort die Existenz eines Users überprüft wird)
        if (identifier.isUnique()) {

            return exists("users", identifier.getColumnName(), value);
        } else {
            log.warn(
                    "Es konnte nicht überprüft werden, ob der User existiert, da die Spalte " + identifier
                            .getColumnName() + " nicht einzigartig ist");
            return false;
        }
    }

    /**
     * Zaehlt die Anzahl der Resultate einer Abfrage
     *
     * @param result ResultSet, in dem die Eintraege gezaehlt werden soll
     * @return Anzahl der Eintraege in result
     */
    private int countResults(ResultSet result) {
        int count;
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
     * @param table  Zu Ueberpruefende Tabelle
     * @param column Spalte der Tabelle
     * @param value  Wert, auf den ueberprueft werden soll
     * @return true, wenn Eintrag existiert
     */
    private boolean exists(String table, String column, Object value) {
        PreparedStatement stmt;

        try {
            // Query ausführen
            stmt = this.dbUserHelper.getConnection()
                    .prepareStatement("SELECT id FROM " + table + " WHERE LOWER(" + column + ") = LOWER(?)");
            stmt.setObject(1, value);
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
            log.error("Es konnte nicht überorüft werden, ob ein Datensatz existiert", e);
        } finally {
            this.dbUserHelper.close();
        }
        return false;
    }
}
