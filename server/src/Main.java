public class Main {
    public static void main(String[] args) {
        ClientAPI api = new ClientAPI(); // Test-ClientAPI Objekt
        UserManager userManager = new UserManager(); // Test-UserManager Objekt
		
		/* Beispiel für Anlegen eines Users 
		 * 
		 * Später sollte diese Funktion in der ClientAPI-Klasse
		 * in der register()-Methode aufgerufen werden, wenn
		 * der Server eine Register-Anfrage vom Client erhält
		 */
        userManager.createUser("testuser", "test", "test@gmail.com");
		
		/* Beispiel für Login
		 * 
		 * Später sollte diese Funktion aufgerufen werden, wenn
		 * der Server eine Login-Anfrage vom Client erhält 
		 */
        String loginUsername = "testuser";
        String loginPassword = "test";

        api.login(loginUsername, loginPassword);
		
		/* Beispiel für Löschen eines Users 
		 * 
		 * Wann und ob diese Funktion jemals aufgerufen wird,
		 * weiß ich auch nicht so genau :D
		 */
        userManager.deleteUser("testuser");
    }
}