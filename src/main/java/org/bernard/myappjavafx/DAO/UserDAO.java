package org.bernard.myappjavafx.DAO;

/**
 * @author BERNANE
 **/

import org.bernard.myappjavafx.Models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

        //  Récupérer tous les utilisateurs
        public static List<User> getAllUsers() {
            List<User> users = new ArrayList<>();
            String query = "SELECT * FROM user";

            try (Connection conn = Configuration.GetConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    users.add(new User(
                            rs.getInt("idUser"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getBoolean("isAdmin")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return users;
        }

        //  Ajouter un utilisateur
        public static void addUser(User user) {
            String query = "INSERT INTO user (login, password, isAdmin) VALUES (?, ?, ?)";

            try (Connection conn = Configuration.GetConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPassword());
                stmt.setBoolean(3, user.isAdmin());
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //  Modifier un utilisateur
        public static void updateUser(User user) {
            String query = "UPDATE user SET login = ?, password = ?, isAdmin = ? WHERE idUser = ?";

            try (Connection conn = Configuration.GetConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, user.getLogin());
                stmt.setString(2, user.getPassword());
                stmt.setBoolean(3, user.isAdmin());
                stmt.setInt(4, user.getIdUser());
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //  Supprimer un utilisateur
        public static void deleteUser(int idUser) {
            String query = "DELETE FROM user WHERE idUser = ?";

            try (Connection conn = Configuration.GetConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, idUser);
                stmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //  Récupérer un utilisateur par login (Pour l'authentification)
        public static User getUserByLogin(String login) {
            User user = null;
            String query = "SELECT * FROM user WHERE login = ?";

            try (Connection conn = Configuration.GetConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, login);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    user = new User(
                            rs.getInt("idUser"),
                            rs.getString("login"),
                            rs.getString("password"),
                            rs.getBoolean("isAdmin")
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return user;
        }

}

