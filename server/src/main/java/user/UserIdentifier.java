package user;

public enum UserIdentifier {
    ID("id", true, false),
    USERNAME("username", true, false),
    EMAIL("email", true, true),
    PASSWORD("password", false, true);

    // Name der Spalte in Datenbank
    private String columnName;

    // Legt fest, ob die Spalte einzigartig ist und ob durch diese eine eindeutige Feststellung des Users stattfinden kann
    private boolean unique;

    // Legt fest, ob die Spalte geändert werden darf
    private boolean changeable;

    UserIdentifier(String columnName, boolean unique, boolean changeable) {
        this.columnName = columnName;
        this.unique = unique;
        this.changeable = changeable;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public boolean isUnique() {
        return this.unique;
    }

    public boolean isChangeable() {
        return this.changeable;
    }
}
