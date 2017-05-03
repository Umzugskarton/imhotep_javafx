import java.sql.*;

import org.apache.commons.codec.digest.DigestUtils;

public class ClientAPI {
    private DBController dbController;
    private UserManager userManager;

    public ClientAPI() {
        this.dbController = new DBController();
        this.dbController.connect();

        this.userManager = new UserManager();
    }

    /**
     * Validiert die Eingaben eines Users für Login
     *
     * @param	username	Username
     * @param	password	Passwort
     * @return				true, wenn Logindaten korrekt sind
     */
    public boolean validateLogin(String username, String password) {
        // Passwort hashen
        String hashedPassword = DigestUtils.md5Hex(password);

        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            // Prüfen, ob Username und gehashtes PW in Datenbank existieren
            String query = "SELECT id, username, password, email FROM users WHERE username = ? AND password = ? LIMIT 0,1";
            stmt = this.dbController.getConnection().prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            result = stmt.executeQuery();

            // Anzahl Einträge mit username und gehashtem Passwort
            int rowCount = this.dbController.countResults(result);

            // Wenn result einen Eintrag hat, also Logindaten stimmen, true zurückgeben, sonst false
            if(rowCount == 1)
                return true;
        } catch (SQLException se) {
            // TODO Auto-generated catch block
            se.printStackTrace();
        } finally {
            try {
                // Statement und ResultSet freigeben
                if(stmt != null) stmt.close();
                if(result != null) result.close();
            } catch (SQLException se) {
                // TODO Auto-generated catch block
                se.printStackTrace();
            }
        }

        return false;
    }

    /**
     * Wenn Client eine Login-Anfrage sendet, werden die Logindaten
     * via validateLogin überprüft. Wenn die Daten korrekt sind, wird
     * eine entsprechende Session an den Client zurückgesendet.
     * Wenn Logindaten inkorrekt sind, wird eine Fehlermeldung an den
     * Client gesendet.
     *
     * @param	username	Username aus JSON-Anfrage
     * @param	password	Passwort aus JSON-Anfrage
     * @return              true, wenn Loginvorgang erfolgreich ausgeführt wurde
     */
    public boolean login(String username, String password) {
        if(this.validateLogin(username, password)) {

			/*
			 * User einloggen, Session zum Client zurücksenden
			 */

            User user = userManager.getUserByUsername(username);
            System.out.println("Login mit Daten (" + username + ", " + password + ") erfolgreich, User-Objekt:");
            System.out.println("\tID: " + user.getId() + "\n\tUsername: " + user.getUsername() + "\n\tPasswort: " + user.getPasswordHash() + "\n\tE-Mail: " + user.getEmail());

            return true;
        } else {

			/*
			 * Fehlermeldung aufgrund inkorrekter logindaten zum client zurücksenden
			 */

            System.out.println("Login mit Daten (" + username + ", " + password + ") fehlgeschlagen, Daten sind inkorrekt");

            return false;
        }
    }

    public void logout() {
        // ...
    }

    public void register() {
        // ...
    }
}