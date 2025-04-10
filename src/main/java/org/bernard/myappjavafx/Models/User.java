package org.bernard.myappjavafx.Models;

/**
 * @author BERNANE
 **/

public class User {
    private int idUser;
    private String login;
    private String password;
    private boolean isAdmin;
    private int idAdmin;

    // Constructeur pour ajouter un nouvel utilisateur
    public User(String login, String password, int idAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = false; // Par défaut, pas admin
        this.idAdmin = idAdmin;
    }

    // Constructeur pour récupérer un utilisateur de la base de données
    public User(int idUser, String login, String password, boolean isAdmin) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        // `idAdmin` peut être défini par défaut ou à partir de la base de données si nécessaire.
        this.idAdmin = 0; // Valeur par défaut si non utilisée
    }

    // Getters et Setters
    public int getIdUser() { return idUser; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }
    public int getIdAdmin() { return idAdmin; }
    public void setIdAdmin(int idAdmin) { this.idAdmin = idAdmin; }

    @Override
    public String toString() {
        return "User{id=" + idUser + ", login='" + login + "', isAdmin=" + isAdmin + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return login.equals(user.login) && password.equals(user.password);
    }

    public boolean authentification(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }

    public void resetPassword(String newPassword) {
        this.password = newPassword;
    }
}
